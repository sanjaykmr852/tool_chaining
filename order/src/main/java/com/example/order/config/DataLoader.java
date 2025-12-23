package com.example.order.config;

import com.example.order.entity.Order;
import com.example.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final OrderRepository orderRepo;

    @Override
    public void run(String... args) throws Exception {

        // For users 1..100 create between 5 and 10 orders each
        for (long userId = 1; userId <= 100; userId++) {
            int orderCount = ThreadLocalRandom.current().nextInt(5, 11); // 5..10 inclusive

            for (int i = 1; i <= orderCount; i++) {
                Order order = Order.builder()
                        .userId(userId)
                        .productName("Product-" + i)
                        .amount(Math.round(ThreadLocalRandom.current().nextDouble(100, 5000) * 100.0) / 100.0)
                        .createdAt(java.time.LocalDateTime.now())
                        .build();
                orderRepo.save(order);
            }
        }
    }
}
