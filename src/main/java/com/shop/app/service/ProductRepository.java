package com.shop.app.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.shop.app.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends  CrudRepository<Product, Integer>{

    @Query("FROM Product WHERE  LOWER(name) = ?1")
    Product findByName(String name);

    @Query("FROM Product WHERE id = ?1")
    Product timTheoId(Integer id);
}
