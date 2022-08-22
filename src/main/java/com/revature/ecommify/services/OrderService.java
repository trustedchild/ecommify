package com.revature.ecommify.services;

import com.revature.ecommify.daos.OrderDAO;
import com.revature.ecommify.models.Order;

import java.util.List;

public class OrderService {
    public final OrderDAO orderDAO;

    public OrderService(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    public List<Order> getAllOrders(){
        return orderDAO.getAll();
    }

    public List<Order> getAllOrdersByUserId(String id){
        return orderDAO.getAllByUserId(id);
    }
}
