package com.example.transaction.config;

import com.example.transaction.entity.Transaction;
import com.example.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

 private final TransactionRepository repo;

 @Override
 public void run(String... args) {
   // For 500–1000 orders generate 1 transaction each
   long orderId=1;
   for(int i=0;i<700;i++){
     double amount = ThreadLocalRandom.current().nextDouble(100,5000);
     Transaction tx = Transaction.builder()
         .orderId(orderId++)
         .amount(Math.round(amount*100)/100.0)
         .status("COMPLETED")
         .createdAt(java.time.LocalDateTime.now())
         .build();
     repo.save(tx);
   }
 }
}
