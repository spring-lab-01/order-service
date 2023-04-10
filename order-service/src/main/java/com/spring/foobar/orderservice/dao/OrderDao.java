package com.spring.foobar.orderservice.dao;

import com.spring.foobar.orderservice.beans.Order;
import com.spring.foobar.orderservice.beans.OrderMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class OrderDao {

    private final JdbcTemplate writeJdbcTemplate;

    private final JdbcTemplate readJdbcTemplate;
    private final String INSERT_ORDER = "insert into \"order\"(id, customer_id, order_date, product, status) values(?,?,?,?,?)";
    private final String SQL_NEXT_SEQ = "select nextval('order_id_seq')";
    private final String SQL_SELECT_ORDER = "select * from order_read.order where id = ?";

    public OrderDao(@Qualifier("writeJdbcTemplate") JdbcTemplate writeJdbcTemplate, @Qualifier("readJdbcTemplate")JdbcTemplate readJdbcTemplate) {
        this.writeJdbcTemplate = writeJdbcTemplate;
        this.readJdbcTemplate = readJdbcTemplate;
    }

    @Transactional
    public Order createOrder(Order order) {
        long id = writeJdbcTemplate.queryForObject(SQL_NEXT_SEQ, Long.class);
        order.setId(id);
        boolean created = writeJdbcTemplate.update(INSERT_ORDER, order.getId(), order.getCustomerId(), order.getOrderDate(), order.getProduct(), order.getStatus().name()) > 0;
        if (created)
            return order;
        else throw new RuntimeException("Exception in creating order");
    }

    public Order getOrderById(long id) {
        try {
            return readJdbcTemplate.queryForObject(SQL_SELECT_ORDER, new OrderMapper(), id);
        }catch (EmptyResultDataAccessException e){
            throw new RuntimeException("Order not found with id "+id);
        }
    }
}
