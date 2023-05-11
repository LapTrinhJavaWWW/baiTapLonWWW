package com.shop.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shop.app.model.entites.Product;

public interface ProductService {
    Product save(Product entity);

    Page<Product> findAll(Pageable pageable);

    void deleteById(Integer id);

    List<Product> findByName(String id);

    Product findById(Integer id);

    int countProduct();

    List<Product> findByNameContaining(String name);

    List<Product> findBySlugContaining(String slug);

    List<Product> findByTypeContaining(String slug);

    List<Product> findBySlugContainingOrNameContainingOrTypeContaining(String slug, String name, String type);

}
