package com.example.L13.L13.service;

import com.example.L13.L13.config.UserInfoConfig;
import com.example.L13.L13.exceptions.*;
import com.example.L13.L13.models.*;
import com.example.L13.L13.repository.BookRepository;
import com.example.L13.L13.repository.OrderRepository;

import com.example.L13.L13.repository.UserRepository;
import com.example.L13.L13.requests.CreateOrderRequestDTO;
import com.example.L13.L13.requests.UpdateOrderRequestDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserInfoConfig userInfoConfig;

    @Autowired
    BookService bookService;

    public Orders createOrder(CreateOrderRequestDTO createOrderRequestDTO){
        Orders orders = createOrderRequestDTO.toOrder();
        return saveOrUpdate(orders);
    }


    public Orders updateOrder(UpdateOrderRequestDTO order){
        Optional<Orders> byOrderReference = orderRepository.findByOrderReference(order.getOrderReferenceId());
        if(!byOrderReference.isPresent()){
            throw new OrderNotFoundException(StatusCode.CHEGG_02);
        }

        Orders existingOrder = byOrderReference.get();
        existingOrder.setOrderStatus(OrderStatus.valueOf(order.getOrderStatus()));
        existingOrder.setAmount(order.getAmount());

        return saveOrUpdate(existingOrder);
    }
    public Orders saveOrUpdate(Orders order){
        return orderRepository.save(order);
    }

    public Optional<Orders> fetchOrderById(String orderReference){
        return orderRepository.findByOrderReference(orderReference);
    }


    /**
     * issue a book to a user
     * 1) Validate the user - check if user exists
     * 2) validate the book - check if book exists and book is not issued
     * 3) check if student has reached a quota for books (restrict b2b sellers - meesho)
     * 4) Order with a pending state;
     * 5) mark the book unavailable and issue that to the user
     * 6) update the order with success status
     */


    public Orders placeOrder(Long userId, Long bookId){

        //fetch user if exists
        Optional<UserInfo> user = userRepository.findById(userId);
        if(user.isPresent()){
            throw new UserNotFoundException();
        }
        UserInfo transactingUser = user.get();
        if(userInfoConfig.getBookQuota() >= transactingUser.getOrdersList().size()){
            throw new BookQuotaExceeded();
        }

        //fetch book if exists and Book Quota has not exceeded
        Optional<Book> book = bookRepository.findById(bookId);
        if(!book.isPresent()){
            throw new BookNotFoundException();
        }
        Book catalogue = book.get();
        if(catalogue.getBookStatus().equals(BookStatus.UNAVAILABLE)){
            throw new BookNotAvailableException();
        }

     // 4) Order with a pending state;
        Orders orders = Orders.builder()
                .orderReference(UUID.randomUUID().toString())
                .orderStatus(OrderStatus.PENDING)
                .amount(catalogue.getCost())
                .userInfo(transactingUser)
                .associatedBook(catalogue)
                .build();
        try{
            saveOrUpdate(orders);

            // 5) mark the book unavailable and issue that to the user
            catalogue.setBookStatus(BookStatus.UNAVAILABLE);
            catalogue.setUser(transactingUser);

            bookService.saveOrUpdate(catalogue);
            // 6) update the order with success status

            orders.setOrderStatus(OrderStatus.SUCCESS);
            saveOrUpdate(orders);

        }catch(Exception e){

            //Roll Back
            catalogue.setBookStatus(BookStatus.AVAILABLE);
            catalogue.setUser(null);
            bookService.saveOrUpdate(catalogue);

            //At this point order has persisted with state PENDING,
            // so get Order Id and update order status

            if(Objects.nonNull(orders.getId())){
                orders.setOrderStatus(OrderStatus.FAILED);
                saveOrUpdate(orders);
            }
        }
        return orders;
    }
}
