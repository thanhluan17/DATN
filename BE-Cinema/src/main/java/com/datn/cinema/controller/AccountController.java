package com.datn.cinema.controller;

import com.datn.cinema.entity.Account;
import com.datn.cinema.entity.User;
import com.datn.cinema.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/account/", params = {"username"})
    public ResponseEntity<User> getUserByUserName(@RequestParam("username") String username) {
        Account account = this.accountService.findByUsername(username);
        if (account == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
