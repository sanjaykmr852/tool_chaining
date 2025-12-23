package com.example.order.service;

import com.example.order.entity.Order;
import com.example.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepo;

    @Override
    public Order addOrder(Order order) {
        order.setCreatedAt(LocalDateTime.now());
        return orderRepo.save(order);
    }

    @Override
    public List<Order> getOrdersByUser(Long userId) {
        return orderRepo.findByUserId(userId);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }
}
