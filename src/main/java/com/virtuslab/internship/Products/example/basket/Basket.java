package com.virtuslab.internship.Products.example.basket;


import com.virtuslab.internship.Products.example.product.Product;

import java.util.ArrayList;
import java.util.List;

public class Basket {

    private final List<Product> products;

    public Basket(List<Product> products) {
        this.products = products;
    }

    public Basket() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }
}
