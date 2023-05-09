package com.shop.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.app.model.Account;

public interface UserRepository extends JpaRepository<Account, Integer>{
    
    Optional<Account> findByEmail(String email);

    Boolean existsByEmail(String email);
}
