package com.spring.foobar.orderservice.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private long id;
    @NonNull
    private long customerId;
    private LocalDateTime orderDate;
    @NonNull
    private String product;
    private OrderStatus status;
}
