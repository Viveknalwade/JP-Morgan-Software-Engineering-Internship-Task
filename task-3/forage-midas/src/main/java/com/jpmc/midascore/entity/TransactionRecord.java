package com.jpmc.midascore.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "transaction_record")
public class TransactionRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private UserRecord sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id", nullable = false)
    private UserRecord recipient;

    private float amount;

    protected TransactionRecord() {}

    public TransactionRecord(UserRecord sender,
                             UserRecord recipient,
                             float amount) {
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
    }
    
    public float getAmount() {
        return amount;
    }
}
