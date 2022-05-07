package com.datn.cinema.service.impl;

import com.datn.cinema.entity.Account;
import com.datn.cinema.entity.AccountRole;
import com.datn.cinema.repository.AccountRoleRepository;
import com.datn.cinema.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountRoleRepository accountRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountService.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("Account not found with username: " + username);
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        List<AccountRole> listAccountRole = accountRoleRepository.findByAccount(account);

        if (listAccountRole != null) {
            for (AccountRole accountRole : listAccountRole) {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(accountRole.getRole().getRoleName());
                grantedAuthorities.add(grantedAuthority);
            }
        }
        return (UserDetails) new User(account.getUsername(), account.getPassword(), grantedAuthorities);
    }
}
