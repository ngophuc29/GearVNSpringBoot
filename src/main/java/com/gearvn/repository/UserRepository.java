package com.gearvn.repository;

 
import org.springframework.data.jpa.repository.JpaRepository;

import com.gearvn.entities.Account;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Account, Integer> {
    // find by email
    Optional<Account> findByEmail(String email);

    // exist email
    boolean existsByEmail(String email);
    
	List<Account> findByUsername(String username);
}
