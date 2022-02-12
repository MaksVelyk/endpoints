package com.cookwarestore.database;

import com.cookwarestore.model.Order;

import java.util.List;

public interface IOrderDAOI {
    void addOrder(Order order);
    List<Order> getOrdersByUserId(int userId);
}
