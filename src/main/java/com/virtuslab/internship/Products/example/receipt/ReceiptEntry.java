package com.virtuslab.internship.Products.example.receipt;

import com.virtuslab.internship.Products.example.product.Product;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

public record ReceiptEntry(
        Product product,
        AtomicInteger quantity,
        BigDecimal totalPrice) {

    public ReceiptEntry(Product product, int quantity) {
        this(product, new AtomicInteger(quantity), product.price().multiply(BigDecimal.valueOf(quantity)));
    }
    public boolean containsProduct(Product product){
        return this.product.equals(product);
    }
    public void increaseQuantity() {
        quantity.incrementAndGet();
    }
}
