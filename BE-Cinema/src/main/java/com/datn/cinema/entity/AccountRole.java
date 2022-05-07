package com.datn.cinema.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "account_role",
        uniqueConstraints = {
                @UniqueConstraint(name = "ACC_ROLE_UK", columnNames = {"username", "role_id"})
        })
public class AccountRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_role_id")
    private Integer accountRoleId;

    @ManyToOne
    @JoinColumn(name = "username")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

}
