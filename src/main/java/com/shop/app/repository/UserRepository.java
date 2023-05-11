package com.shop.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shop.app.model.entites.Account;

public interface UserRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findByEmail(String email);

    Boolean existsByEmail(String email);

    // count user
    @Query(value = "SELECT COUNT(*) FROM Account", nativeQuery = true)
    int countAccount();

    // tim kiem ten user theo chuoi con
    List<Account> findByLastNameContainingOrFirstNameContaining(String name, String name2);
}
