package com.cookwarestore.service.impl;

import com.cookwarestore.model.Cookware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cookwarestore.database.ICookwareDAOI;
import com.cookwarestore.database.IOrderDAOI;
import com.cookwarestore.model.Order;
import com.cookwarestore.model.OrderPosition;
import com.cookwarestore.service.IOrderServI;
import com.cookwarestore.session.SessionObject;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServNew implements IOrderServI {

    @Resource
    SessionObject sessionObject;

    @Autowired
    IOrderDAOI orderDAO;

    @Autowired
    ICookwareDAOI cookwareDAO;

    @Override
    public void confirmOrder() {
        Order order = new Order(this.sessionObject.getUser(), new HashSet<>(this.sessionObject.getCart().getOrderPositions()));
        this.orderDAO.addOrder(order);
        for (OrderPosition orderPosition : order.getOrderPositions()) {
            Optional<Cookware> toolBox = this.cookwareDAO.getCookwareById(orderPosition.getCookware().getId());
            if(toolBox.isPresent()) {
                toolBox.get().setQuantity(toolBox.get().getQuantity() - orderPosition.getQuantity());
                this.cookwareDAO.updateCookware(toolBox.get());
            }
        }
        this.sessionObject.getCart().clearOrderPositions();
    }

    @Override
    public List<Order> getOrdersForCurrentUser() {
        return this.orderDAO.getOrdersByUserId(this.sessionObject.getUser().getId());
    }
}
