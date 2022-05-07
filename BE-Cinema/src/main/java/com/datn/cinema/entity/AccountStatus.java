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
@Table(name = "account_status")
public class AccountStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_status_id")
    private Integer accountStatusId;

    @Column(name = "account_status_name", columnDefinition = "varchar(50)")
    private String accountStatusName;

    @OneToMany(mappedBy = "accountStatus")
    @JsonIgnore
    private Set<Account> accountSet;
}
