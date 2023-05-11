package com.shop.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shop.app.model.entites.Account;
import com.shop.app.repository.UserRepository;
import com.shop.app.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    @Override
    public Optional<Account> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public int countAccount() {
        return repository.countAccount();
    }

    @Override
    public Page<Account> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<Account> findByLastNameContainingOrFirstNameContaining(String name, String name2) {
        return repository.findByLastNameContainingOrFirstNameContaining(name, name2);
    }
}
