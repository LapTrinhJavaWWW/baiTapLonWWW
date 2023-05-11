package com.shop.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<Product> findAll(Pageable pageable) {
        return repository.findAll(pageable);
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

    @Override
    public int countProduct() {
        return repository.countProduct();
    }

    @Override
    public List<Product> findByNameContaining(String name) {
        return repository.findByNameContaining(name);
    }

    @Override
    public List<Product> findBySlugContaining(String slug) {
        return repository.findBySlugContaining(slug);
    }

    @Override
    public List<Product> findByTypeContaining(String slug) {
        return repository.findByTypeContaining(slug);
    }

    @Override
    public List<Product> findBySlugContainingOrNameContainingOrTypeContaining(String slug, String name, String type) {
        return repository.findBySlugContainingOrNameContainingOrTypeContaining(slug, name, type);
    }

}
