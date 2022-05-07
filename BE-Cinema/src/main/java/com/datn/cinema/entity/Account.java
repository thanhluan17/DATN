package com.datn.cinema.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "account",
        uniqueConstraints = {
                @UniqueConstraint(name = "ACC_UK", columnNames = "username")
        })
public class Account {

    @Id
    @Column(name = "username", columnDefinition = "VARCHAR(50) UNIQUE NOT NULL")
    private String username;

    @Column(name = "`password`", columnDefinition = "VARCHAR(255)")
    private String password;

    @Column(name = "register_date", columnDefinition = "DATE")
    private String registerDate;

    @ManyToOne
    @JoinColumn(name = "account_status_id", referencedColumnName = "account_status_id")
    private AccountStatus accountStatus;

    @OneToOne(mappedBy = "account")
    @JsonIgnore
    private User user;

    @Column(name = "point", columnDefinition = "varchar(50)")
    private String point;

    @OneToMany(mappedBy = "account")
    @JsonIgnore
    private Set<Notification> notificationSet;

    @OneToMany(mappedBy = "account")
    @JsonIgnore
    private Set<TransactionHistory> transactionHistorySet;
}

