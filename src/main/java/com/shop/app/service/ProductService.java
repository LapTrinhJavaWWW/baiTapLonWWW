package com.shop.app.service;

import java.util.List;

import com.shop.app.model.entites.Product;

public interface ProductService {
    Product save(Product entity);

    List<Product> findAll();

    void deleteById(Integer id);

    List<Product> findByName(String id);

    Product findById(Integer id);

}
