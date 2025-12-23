package com.example.transaction.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Transaction {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long transactionId;

 private Long orderId;
 private Double amount;
 private String status; 
 private LocalDateTime createdAt;
}
