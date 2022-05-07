
package com.datn.cinema.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "transaction_history")

public class TransactionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Integer transactionId;

    @Column(name = "transaction_date", columnDefinition = "date")
    private String transactionDate;

    @ManyToOne
    @JoinColumn(name = "username", columnDefinition = "varchar(50)")
    private Account account;

    @Column(name = "description", columnDefinition = "varchar(50)")
    private String description;

    @Column(name = "status", columnDefinition = "bit")
    private boolean status;
}
