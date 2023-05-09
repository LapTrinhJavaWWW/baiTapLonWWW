package com.shop.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.app.model.Product;
import com.shop.app.service.ProductRepository;
import com.shop.app.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository repository;

    public Product save(Product entity) {
        return repository.save(entity);
    }

    public <S extends Product> Iterable<S> saveAll(Iterable<S> entities) {
        return repository.saveAll(entities);
    }

    public Optional<Product> findById(Integer id) {
        return repository.findById(id);
    }

    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    public List<Product> findAll() {
        return (List<Product>) repository.findAll();
    }

    public Iterable<Product> findAllById(Iterable<Integer> ids) {
        return repository.findAllById(ids);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Product findByName(String name) {
        return  repository.findByName(name);
    }

    @Override
    public Product timTheoId(Integer id) {
        return repository.timTheoId(id);
    }

    public void delete(Product entity) {
        repository.delete(entity);
    }

    public void deleteAllById(Iterable<? extends Integer> ids) {
        repository.deleteAllById(ids);
    }

    public void deleteAll(Iterable<? extends Product> entities) {
        repository.deleteAll(entities);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    
}
