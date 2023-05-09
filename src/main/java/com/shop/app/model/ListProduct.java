package com.shop.app.model;

import java.util.ArrayList;
import java.util.List;

public class ListProduct {
    private List<Product> ds;

    public ListProduct() {
        ds = new ArrayList<Product>();
    }

    public List<Product> getDs() {
        return ds;
    }

    public void setDs(List<Product> ds) {
        this.ds = ds;
    }

    public void add(Product p) {
        ds.add(p);
    }

    public List<Product> search(String value){
        List<Product> list = new ArrayList<Product>();
        ds.forEach(p -> {
            if (p.getName().toLowerCase().contains(value.toLowerCase())) {
                list.add(p);
            }
        });

        return list;
    }

    public List<Product> fillter(List<String> dsbrands, int minPrice, int maxPrice,String brand) {
        List<Product> list = new ArrayList<Product>();
       if(brand.equals("all") && maxPrice == 0) {
           ds.forEach(p -> {
               list.add(p);
            });
        }else if(brand.equals("all") && maxPrice != 0) {
            ds.forEach(p -> {
                if (p.getPrice() >= minPrice * 1000000 && p.getPrice() <= maxPrice * 1000000) {
                    list.add(p);
                }
            });
        }else if(!brand.equals("all") && maxPrice == 0) {
            ds.forEach(p -> {
                dsbrands.forEach(b -> {
                    if (p.getSlug().toLowerCase().equals(b.toLowerCase())) {
                        list.add(p);
                    }
                });
            });
        }else if(!brand.equals("all") && maxPrice != 0) {
            ds.forEach(p -> {
                dsbrands.forEach(b -> {
                    if (p.getSlug().toLowerCase().equals(b.toLowerCase()) && (p.getPrice() >= minPrice * 1000000 && p.getPrice() <= maxPrice * 1000000)) {
                        list.add(p);
                    }
                });
            });
        }
        return list;
    }

    public List<Product> getDsBestSeller() {
        List<Product> list = new ArrayList<Product>();
        ds.forEach(p -> {
            if (p.getBestSeller().equals("on")) {
                list.add(p);
            }
        });
        return list;
    }

    public List<Product> sortPriceMin() {
        List<Product> list = new ArrayList<Product>();
       ds.stream().sorted((p1, p2) -> (int) (p1.getPrice() - p2.getPrice())).forEach(p -> {
           list.add(p);
         });
        return list;
    }
    public List<Product> sortPriceMax() {
        List<Product> list = new ArrayList<Product>();
        ds.stream().sorted((p1, p2) -> (int) (p2.getPrice() - p1.getPrice())).forEach(p -> {
            list.add(p);
        });
        return list;
    }

    public List<Product> sortByName() {
        List<Product> list = new ArrayList<Product>();
        ds.stream().sorted((p1, p2) -> p1.getName().compareTo(p2.getName())).forEach(p -> {
            list.add(p);
        });
        return list;
    }
}
