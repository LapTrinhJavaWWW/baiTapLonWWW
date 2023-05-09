package com.shop.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.app.model.entites.Product;
import com.shop.app.repository.ProductRepository;
import com.shop.app.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository repository;

    @Override
    public Product save(Product entity) {
        return repository.save(entity);
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<Product> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Product findById(Integer id) {
        return repository.findById(id).get();
    }

}
