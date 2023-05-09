package com.shop.app.service;

import java.util.List;

import com.shop.app.model.Product;

public interface ProductService {
    Product save(Product entity);
    List<Product> findAll();
    void deleteById(Integer id);

    Product findByName(String name);

    Product timTheoId(Integer id);

}
