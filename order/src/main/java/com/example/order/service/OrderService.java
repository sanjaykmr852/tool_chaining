package com.example.order.service;

import com.example.order.entity.Order;
import java.util.List;

public interface OrderService {
    Order addOrder(Order order);
    List<Order> getOrdersByUser(Long userId);
    List<Order> getAllOrders();
}
