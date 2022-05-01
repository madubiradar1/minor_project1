package com.example.L13.L13.requests;


import com.example.L13.L13.models.OrderStatus;
import com.example.L13.L13.models.Orders;
import lombok.*;

import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequestDTO {

    @Positive
    double amount;

    @NotBlank
    String orderStatus;

    public Orders toOrder(){

        return Orders.builder()
                .amount(amount)
                .orderReference(UUID.randomUUID().toString())
                .orderStatus(OrderStatus.valueOf(orderStatus))
                .build();
    }
}
