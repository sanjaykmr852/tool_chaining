package com.example.transaction.service;

import com.example.transaction.entity.Transaction;
import com.example.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

 private final TransactionRepository repo;

 @Override
 public Transaction create(Transaction tx) {
   tx.setCreatedAt(LocalDateTime.now());
   return repo.save(tx);
 }

 @Override
 public Transaction getById(Long id) {
   return repo.findById(id).orElse(null);
 }

 @Override
 public List<Transaction> getByOrder(Long orderId) {
   return repo.findByOrderId(orderId);
 }

 @Override
 public List<Transaction> getAll() {
   return repo.findAll();
 }
}
