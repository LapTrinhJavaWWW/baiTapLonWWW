package com.shop.app.service;

import org.springframework.data.repository.CrudRepository;

import com.shop.app.model.Product;

public interface ProductRepository extends  CrudRepository<Product, Integer>{
    
}
