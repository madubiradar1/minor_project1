package com.example.L13.L13.controller.advice;

import com.example.L13.L13.models.Orders;
import com.example.L13.L13.requests.CreateOrderRequestDTO;
import com.example.L13.L13.requests.UpdateOrderRequestDTO;
import com.example.L13.L13.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@Slf4j
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Orders> createOrder(@Valid @RequestBody CreateOrderRequestDTO createOrderRequestDTO){
        log.info("inside controller {}",createOrderRequestDTO);
        return new ResponseEntity<>(orderService.createOrder(createOrderRequestDTO), HttpStatus.CREATED);
    }

    @PutMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Orders> createOrder(@Valid @RequestBody UpdateOrderRequestDTO updateOrderRequestDTO){
        log.info("inside controller {}",updateOrderRequestDTO);
        return new ResponseEntity<>(orderService.updateOrder(updateOrderRequestDTO), HttpStatus.OK);
    }

    @GetMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Orders>> fetchOrder(@RequestParam(value = "orderRef") String orderReference){
        log.info("inside controller {}",orderReference);
        return new ResponseEntity<>(orderService.fetchOrderById(orderReference), HttpStatus.OK);
    }

    @PostMapping(value = "/issue-book", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Orders> placeOrder(@RequestParam(value = "userId") Long userId, @RequestParam(value = "bookId") Long bookId){
        log.info("inside controller {} {}",userId, bookId);
        return new ResponseEntity<Orders>(orderService.placeOrder(userId, bookId), HttpStatus.CREATED);
    }
}
