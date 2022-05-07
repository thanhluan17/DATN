package com.datn.cinema.service.impl;

import com.datn.cinema.dto.AccountDTO;
import com.datn.cinema.entity.Account;
import com.datn.cinema.entity.User;
import com.datn.cinema.repository.AccountRepository;
import com.datn.cinema.service.AccountService;
import com.datn.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;


    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private UserService userService;

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }


    @Override
    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public void deleteUserAccount(String username) {
        accountRepository.deleteUserAccount(username);
    }

    @Override
    public List<Account> findAllAccount() {
        return accountRepository.getListAccount();
    }

    @Override
    public Account getAccountByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public void saveUserAccount(Account account) {
        String statusAccount = "2";
        if (account.getUsername() == null) {
            account.setRegisterDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        }
        accountRepository.saveUserAccount(account.getUsername(), account.getPassword(), LocalDate.now(), statusAccount);
    }

    @Override
    public void sendEmailApprove(String email) throws MessagingException {
        User user = userService.findByEmail(email);
        System.out.println(user.getAccount().getUsername());
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper messageApprove = new MimeMessageHelper(message, "utf-8");
        String mailContent = "<h1 style='color: #FF8C00 '>C11-Cinema</h1>";
        mailContent += "<p>Xin chúc mừng bạn đã đăng kí thành công</p><br>";
        mailContent += "<p>Tài khoản: " + user.getAccount().getUsername() + "</p>";
        mailContent += "<p>Mật khẩu : " + "admin123" + "</p>";
        mailContent += "<p>Điểm     : " + user.getAccount().getPoint() + "</p>";
        mailContent += "<p>Ngày tạo : " + user.getAccount().getRegisterDate() + "</p>";
        mailContent += "<a href=http://localhost:4200/login style='color: lightblue'>Nhấp vào đây để đăng nhập</a>" + "<span> để đến với trang của chúng tôi</span>" +
                "<p>Thanks and regards!</p>";
        messageApprove.setTo(email);
        messageApprove.setSubject("[C11-Cinema]-Thông báo");
        messageApprove.setText(mailContent, true);
        emailSender.send(message);
    }

    @Override
    public void sendEmailDelete(String email) {
        SimpleMailMessage messageDelete = new SimpleMailMessage();
        messageDelete.setTo(email);
        messageDelete.setSubject("Email thông báo khoá tài khoản");
        messageDelete.setText("Xin thông báo! Tin của bạn đã bị xoá do vi phạm!" +
                " Nếu có bất kì thắc mắc nào, bạn có thể liên hệ với Admin email. \n" +
                " Thanks and regards!");
        this.emailSender.send(messageDelete);
    }

    @Override
    public String generateCode() {
        return "" + (new Random().nextInt(900000) + 100000);
    }

    @Override
    public void sendEmail(String email, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Email xác nhận tài khoản");
        message.setText("Chào bạn!\n"
                + "TRANG WEB CINEMA C11 gửi mã code OTP để xác nhận tài khoản.\n"
                + "Mã CODE bao gồm 6 số : " + code + "\n\n"
                + "Thanks and regards!");
        this.emailSender.send(message);
    }


    @Override
    public Account findByAccount(String username) {
        return accountRepository.findByUsername(username);
    }


    @Override
    public Integer setNewPassword(AccountDTO accountDTO) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        accountDTO.setNewPassword(passwordEncoder.encode(accountDTO.getNewPassword()));
        accountRepository.saveAccountDto(accountDTO.getNewPassword(), accountDTO.getUsername());
        return null;
    }

    @Override
    public void sendEmailOTP(String email, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Email lấy lại mật khẩu từ Cinema C11");
        message.setText("Chào bạn!\n"
                + "TRANG Cinema C11 gửi mã code OTP bên dưới để đổi lại mật khẩu.\n"
                + "Mã CODE bao gồm 6 số : " + code + "\n\n"
                + "Thanks and regards!");
        this.emailSender.send(message);
    }

    @Override
    public void changeAccountStatus(String username) {
        accountRepository.changeAccountStatus(username);
    }

    @Override
    public void sendEmailConfirm(String email) throws MessagingException {
        User user = userService.findByEmail(email);
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper messageApprove = new MimeMessageHelper(message, "utf-8");
        String mailContent = "<h1 style='color: #FF8C00 '>C11-Cinema</h1>";
        mailContent += "<p>Xin chúc mừng bạn đã đăng kí thành công</p><br>";
        mailContent += "<p>Tài khoản: " + user.getAccount().getUsername() + "</p>";
        mailContent += "<p>Ngày tạo : " + user.getAccount().getRegisterDate() + "</p>";
        mailContent += "<a href=\"http://localhost:4200/register/confirmEmail/"
                + user.getAccount().getUsername() + "/" + user.getEmail() +
                "\" style='color: lightblue'>Nhấp vào đây để xác nhận tài khoản</a>" +
                "<span> để đến với trang của chúng tôi</span>" +
                "<p>Thanks and regards!</p>";
        messageApprove.setTo(email);
        messageApprove.setSubject("[C11-Cinema]-Thông báo");
        messageApprove.setText(mailContent, true);
        emailSender.send(message);
    }
}
