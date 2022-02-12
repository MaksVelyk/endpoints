package com.cookwarestore.database;

import com.cookwarestore.model.OrderPosition;

import java.util.List;

public interface IOrderPositionDAOI {
    void addOrderPosition(OrderPosition orderPosition, int orderId);
    List<OrderPosition> getOrderPositionsByOrderId(int orderId);
}
