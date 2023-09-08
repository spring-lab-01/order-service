package com.spring.foobar.orderservice.controller;

import com.spring.foobar.orderservice.beans.Order;
import com.spring.foobar.orderservice.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable long id){
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody Order order){
        if(order.getId()!=0){
            throw new RuntimeException("user put instead of post");
        }
        Order savedOrder = orderService.createOrder(order);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(savedOrder.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
