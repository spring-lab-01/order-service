package com.spring.foobar.orderservice.service;

import com.spring.foobar.orderservice.beans.Order;
import com.spring.foobar.orderservice.beans.OrderMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class OrderDao {

    private final JdbcTemplate jdbcTemplate;
    private final String INSERT_ORDER = "insert into \"order\"(id, customer_id, order_date, product, status) values(?,?,?,?,?)";
    private final String SQL_NEXT_SEQ = "select nextval('order_id_seq')";
    private final String SQL_SELECT_ORDER = "select * from \"order\" where id = ?";

    public OrderDao(JdbcTemplate writeJdbcTemplate) {
        this.jdbcTemplate = writeJdbcTemplate;
    }

    @Transactional
    public Order createOrder(Order order) {
        long id = jdbcTemplate.queryForObject(SQL_NEXT_SEQ, Long.class);
        order.setId(id);
        boolean created = jdbcTemplate.update(INSERT_ORDER, order.getId(), order.getCustomerId(), order.getOrderDate(), order.getProduct(), order.getStatus().name()) > 0;
        if (created)
            return order;
        else throw new RuntimeException("Exception in creating order");
    }

    public Order getOrderById(long id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ORDER, new OrderMapper(), id);
        }catch (EmptyResultDataAccessException e){
            throw new RuntimeException("Order not found with id "+id);
        }
    }
}
