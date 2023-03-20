package com.spring.foobar.orderservice.service;

import com.spring.foobar.orderservice.beans.Order;
import com.spring.foobar.orderservice.beans.OrderStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderService {

    private final OrderDao orderDao;

    public OrderService(OrderDao orderDao){
        this.orderDao = orderDao;
    }

    public Order createOrder(Order order) {
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.CREATED);
        return orderDao.createOrder(order);
    }

    public Order getOrderById(long id) {
        return orderDao.getOrderById(id);
    }
}
