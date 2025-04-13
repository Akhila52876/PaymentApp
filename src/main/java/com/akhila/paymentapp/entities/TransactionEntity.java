package com.akhila.paymentapp.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class TransactionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id") 
	private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_user_id", referencedColumnName = "user_id")
    private UserEntity sender;

    @ManyToOne
    @JoinColumn(name = "receiver_user_id", referencedColumnName = "user_id")
    private UserEntity receiver;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "type")
    private String type;  // "WALLET" or "BANK"

    @Column(name = "transaction_type")
    private String transactionType;  // "DEBIT" or "CREDIT"

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getSender() {
        return sender;
    }

    public void setSender(UserEntity sender) {
        this.sender = sender;
    }

    public UserEntity getReceiver() {
        return receiver;
    }

    public void setReceiver(UserEntity receiver) {
        this.receiver = receiver;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
