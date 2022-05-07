package com.datn.cinema.service.impl;

import com.datn.cinema.dto.UserDTO;
import com.datn.cinema.dto.UserNoAccountDTO;
import com.datn.cinema.entity.Account;
import com.datn.cinema.entity.AccountStatus;
import com.datn.cinema.entity.User;
import com.datn.cinema.repository.AccountRepository;
import com.datn.cinema.repository.UserRepository;
import com.datn.cinema.service.AccountService;
import com.datn.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;

    @Override
    public List<User> findAll(int index) {
        return userRepository.getAllUser(index);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById1(Integer userId) {
        return userRepository.findById(userId);
    }

    @Override
    public User findById(Integer userId) {
        return userRepository.findUserById(userId);
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        userRepository.updateUser(userDTO.getUserId(), standardizeName(userDTO.getName()),
                userDTO.getEmail(), userDTO.getPhone(), userDTO.getWard(),
                userDTO.getAvatarUrl(), userDTO.getGender(), userDTO.getBirthday());
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public User findByIdCard(String idCard) {
        return userRepository.getUserByIdCard(idCard);
    }

    @Override
    public void saveUserCus(UserDTO userDTO) {
        String statusConfirmMail = "2";
        Account account = new Account();
        account.setUsername(userDTO.getUsername());
        account.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        if (userDTO.getUsername() == null) {
            account.setRegisterDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        }
        accountRepository.saveUserAccount(account.getUsername(), account.getPassword(), LocalDate.now(), statusConfirmMail);

        User user = new User();

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setWard(userDTO.getWard());
        user.setGender(userDTO.getGender());
        user.setIdCard(userDTO.getIdCard());
        user.setAvatarUrl(userDTO.getAvatarUrl());
        user.setAccount(account);
        userRepository.saveUserCus(user.getAvatarUrl(),
                standardizeName(user.getName()),
                user.getAccount().getUsername(),
                user.getEmail(),
                user.getBirthday(),
                user.getIdCard(),
                user.getGender(),
                user.getPhone(),
                user.getWard().getWardId());
    }

    @Override
    public void deleteUserById(Integer id) {
        User user = userRepository.findUserById(id);
        if (user != null) {
            Account account = user.getAccount();
            if (account != null) {
                AccountStatus accountStatus = new AccountStatus();
                accountStatus.setAccountStatusId(3);
                account.setAccountStatus(accountStatus);
                accountRepository.save(account);
            }
        }
    }

    @Override
    public List<User> searchAllUserAttribute(String key) {
        return userRepository.searchAll(key);
    }

    @Override
    public List<User> findAllUser() {
        return userRepository.findAllUser();
    }

    public String standardizeName(String name) {
        name = name.toLowerCase();
        name = name.replaceAll("\\s+", " ");
        name = name.trim();
        String name2 = "";
        String temp = "";
        String[] words = name.split("\\s");
        for (String w : words) {
            temp = w.substring(0, 1).toUpperCase();
            for (int i = 1; i < w.length(); i++) {
                temp += w.charAt(i);
            }
            name2 += temp + " ";
        }
        name = name2.trim();
        return name;
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public User createUserNoAccount(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByUsername1(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void updateUser(User user, String username) {
        User user1 = this.findByUsername(username);
        if (user1 != null) {
            user.setAccount(user1.getAccount());
            user.setWard(user1.getWard());
            user.setAvatarUrl(user1.getAvatarUrl());
            user.setUserId(user1.getUserId());
            userRepository.save(user);
        }
    }

    @Override
    public void updateUser1(User user) {
        userRepository.updateUser1(user.getName(), user.getBirthday(), user.getGender(), user.getEmail(),
                user.getIdCard(), user.getPhone());
    }

    @Override
    public List<User> searchAllAttributePagination(String q, int index) {
        return userRepository.getListSearchPagination(q, index);
    }

    @Override
    public void saveUserCusConfirm(UserDTO userDTO) {
        String statusConfirmMail = "1";
        Account account = new Account();
        account.setUsername(userDTO.getUsername());
        account.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        if (userDTO.getUsername() == null) {
            account.setRegisterDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        }
        accountRepository.saveUserAccount(account.getUsername(), account.getPassword(), LocalDate.now(), statusConfirmMail);

        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setWard(userDTO.getWard());
        user.setBirthday(userDTO.getBirthday());
        user.setGender(userDTO.getGender());
        user.setIdCard(userDTO.getIdCard());
        user.setAvatarUrl(userDTO.getAvatarUrl());
        user.setAccount(account);
        userRepository.saveUserCus(user.getAvatarUrl(),
                standardizeName(user.getName()),
                user.getAccount().getUsername(),
                user.getEmail(),
                user.getBirthday(),
                user.getIdCard(),
                user.getGender(),
                user.getPhone(),
                user.getWard().getWardId());
    }

    @Override
    public void saveUserSocial(User user) {
        userRepository.saveUserSocial(user.getName(), user.getEmail(), "", user.getAccount().getUsername(),
                user.getAvatarUrl(), null, user.getBirthday(), user.getIdCard(), user.getGender());
    }

    @Override
    public void createUserWithNoAccount(UserNoAccountDTO userNoAccountDTO) {
        userRepository.createUserWithNoAccount(userNoAccountDTO.getEmail(),
                userNoAccountDTO.getIdCard(),
                userNoAccountDTO.getName(),
                userNoAccountDTO.getPhone());
    }

    @Override
    public User getUserByUserNoAccountDTO(UserNoAccountDTO userNoAccountDTO) {
        return userRepository.getUserByUserNoAccountDTO(userNoAccountDTO.getEmail(),
                userNoAccountDTO.getIdCard(),
                userNoAccountDTO.getName(),
                userNoAccountDTO.getPhone());
    }
}
