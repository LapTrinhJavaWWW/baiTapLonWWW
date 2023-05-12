package com.shop.app.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    }

    public void addItem( Product  product) {
        items.add(product);
        totalPrice += product.getPrice();
        System.out.println("kaiiiiiiiiiiiiiiiiii");
        System.out.println(totalPrice);
    }

    public List< Product>  getItems() {
        return items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
