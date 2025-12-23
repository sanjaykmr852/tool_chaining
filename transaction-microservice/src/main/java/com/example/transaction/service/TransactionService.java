package com.example.transaction.service;

import com.example.transaction.entity.Transaction;
import java.util.List;

public interface TransactionService {
 Transaction create(Transaction tx);
 Transaction getById(Long id);
 List<Transaction> getByOrder(Long orderId);
 List<Transaction> getAll();
}
