package com.spring.foobar.orderservice.beans;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class OrderMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        order.setId(rs.getLong("id"));
        order.setCustomerId(rs.getLong("customer_id"));
        order.setOrderDate(rs.getObject("order_date", LocalDateTime.class));
        order.setProduct(rs.getString("product"));
        order.setStatus(OrderStatus.valueOf(rs.getString("status")));
        return order;
    }
}
