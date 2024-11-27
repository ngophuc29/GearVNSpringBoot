package com.gearvn.service;

 

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;

import com.gearvn.entities.Account;

public interface UserService {
    Optional<Account> findByEmail(String email);

    Boolean existsByEmail(String email);

    List<Account> findByUsername(String username);
    
    
    public UserDetails loadUserByUsername(String username);
    
    public Account registerCustomer(Account account);
    Account save(Account entity);
}
