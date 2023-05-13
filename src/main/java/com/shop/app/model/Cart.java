package com.shop.app.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.*;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue
    @Column(name = "cart_id", length = 50)
    private Integer id;

    @OneToMany(mappedBy = "cart")
    @Column(name = "items" )
    private List <Product>  items;

    private double totalPrice;

    public Cart() {
        items = new ArrayList <Product> ();
        totalPrice = 0.0;
        productAmounts = new HashMap<Integer, Integer>();
    }

    public void addItem( Product  product) {
        int productId = product.getId();
        int defaultAmount = 1; // Số lượng mặc định khi thêm sản phẩm vào giỏ hàng

        if (productAmounts.containsKey(productId)) {
            // Sản phẩm đã tồn tại trong giỏ hàng, cập nhật số lượng
            int currentAmount = productAmounts.get(productId);
            productAmounts.put(productId, currentAmount + defaultAmount);
            totalPrice += product.getPrice();
        } else {
            // Sản phẩm chưa tồn tại trong giỏ hàng, thêm vào với số lượng mới
            productAmounts.put(productId, defaultAmount);
            items.add(product);
            totalPrice += product.getPrice();
        }
    }

    public List< Product>  getItems() {
        return items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @ElementCollection
    @CollectionTable(name = "cart_product_amounts", joinColumns = @JoinColumn(name = "cart_id"))
    @MapKeyColumn(name = "product_id")
    @Column(name = "amount")
    private Map<Integer, Integer> productAmounts;

    public Map<Integer, Integer> getProductAmounts() {
        return productAmounts;
    }

    public void setProductAmounts(Map<Integer, Integer> productAmounts) {
        this.productAmounts = productAmounts;
    }




}