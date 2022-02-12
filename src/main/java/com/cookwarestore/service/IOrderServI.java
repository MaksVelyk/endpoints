package com.cookwarestore.service;

import com.cookwarestore.model.Order;

import java.util.List;

public interface IOrderServI {
    void confirmOrder();
    List<Order> getOrdersForCurrentUser();
}
