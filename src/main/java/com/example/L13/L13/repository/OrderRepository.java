package com.example.L13.L13.repository;

import com.example.L13.L13.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Long> {

 /* JPQL - modern way
  */

   Optional<Orders> findByOrderReference(String orderRef);

  //@Query(value = "select * from orders where order_reference = orderReference", nativeQuery = true)
  //Optional<Orders> findOrdersByOrderReference(String orderReference);
}
