package com.datn.cinema.service.impl;

import com.datn.cinema.repository.AccountRoleRepository;
import com.datn.cinema.service.AccountRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountRoleServiceImpl implements AccountRoleService {
    @Autowired
    private AccountRoleRepository accountRoleRepository;

    @Override
    public void saveAccountRoleUser(String username, int i) {
        accountRoleRepository.saveAccountRole(username, i);
    }
}
