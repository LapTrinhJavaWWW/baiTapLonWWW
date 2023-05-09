package com.shop.app.service;

import java.util.List;
import java.util.Optional;

import com.shop.app.model.Product;

public interface ProductService {
    Product save(Product entity);
    List<Product> findAll();
    void deleteById(Integer id);

    Product findById(Integer id);



}
