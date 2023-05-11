package com.shop.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shop.app.model.entites.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Page<Product> findAll(Pageable pageable);

    void deleteById(Integer id);

    List<Product> findByName(String name);

    Optional<Product> findById(Integer id);

    List<Product> findBySlugContaining(String slug);

    List<Product> findByTypeContaining(String slug);

    // get number page
    @Query(value = "SELECT COUNT(*) FROM Product", nativeQuery = true)
    int countProduct();

    // lay san offset 5 san pham limit 5 san pham

    List<Product> findTop5ByIdLessThanOrderByIdDesc(Integer id);

    // lay 5 san pham truoc do
    List<Product> findTop3ByIdGreaterThan(Integer id);

    // tìm kiếm sản phẩm theo chuoi con
    List<Product> findByNameContaining(String name);

    // tim kiem san pham theo chuoi con slug va name va type
    // Page<Product> findBySlugContainingOrNameContainingOrTypeContaining(String
    // name, String name2, String name3,
    // Pageable pageable);

    List<Product> findBySlugContainingOrNameContainingOrTypeContaining(String name, String name2, String name3);

}
