package com.datn.cinema.service;

import com.datn.cinema.dto.AccountDTO;
import com.datn.cinema.entity.Account;

import javax.mail.MessagingException;
import java.util.List;

public interface AccountService {

    List<Account> findAll();

    void deleteUserAccount(String username);

    List<Account> findAllAccount();

    Account getAccountByUsername(String username);

    void saveUserAccount(Account account);

    void sendEmailDelete(String email) throws MessagingException;

    void sendEmailApprove(String email) throws MessagingException;

    Account findByUsername(String username);

    String generateCode();

    void sendEmail(String email, String code);

    void changeAccountStatus(String username);

    Account findByAccount(String username);

    Integer setNewPassword(AccountDTO accountDTO);

    void sendEmailOTP(String email, String code);

    void sendEmailConfirm(String email) throws MessagingException;
}
