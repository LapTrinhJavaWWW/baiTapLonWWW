package com.shop.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shop.app.model.entites.Account;

public interface UserService {

    Optional<Account> findByEmail(String email);

    Boolean existsByEmail(String email);

    int countAccount();

    List<Account> findByLastNameContainingOrFirstNameContaining(String name, String name2);

    Page<Account> findAll(Pageable pageable);
}
