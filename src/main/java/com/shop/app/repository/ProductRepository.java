package com.shop.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.app.model.entites.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product save(Product entity);

    List<Product> findAll();

    void deleteById(Integer id);

    List<Product> findByName(String name);

    Optional<Product> findById(Integer id);

    List<Product> findByCategoryId(Integer id);

}
