package com.example.transaction.controller;

import com.example.transaction.entity.Transaction;
import com.example.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

 private final TransactionService service;

 @PostMapping
 public Transaction create(@RequestBody Transaction tx){
   return service.create(tx);
 }

 @GetMapping("/{id}")
 public Transaction getById(@PathVariable Long id){
   return service.getById(id);
 }

 @GetMapping("/order/{orderId}")
 public List<Transaction> getByOrder(@PathVariable Long orderId){
   return service.getByOrder(orderId);
 }

 @GetMapping
 public List<Transaction> getAll(){
   return service.getAll();
 }
}
