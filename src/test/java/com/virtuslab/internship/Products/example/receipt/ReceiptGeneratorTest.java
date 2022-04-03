package com.virtuslab.internship.Products.example.receipt;

import com.virtuslab.internship.Products.example.basket.Basket;
import com.virtuslab.internship.Products.example.discount.FifteenPercentDiscount;
import com.virtuslab.internship.Products.example.discount.TenPercentDiscount;
import com.virtuslab.internship.Products.example.product.ProductDb;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ReceiptGeneratorTest {

    @Test
    void shouldGenerateReceiptForGivenBasket() {
        // Given
        var productDb = new ProductDb();
        var cart = new Basket();
        var milk = productDb.getProduct("Milk");
        var bread = productDb.getProduct("Bread");
        var apple = productDb.getProduct("Apple");
        var expectedTotalPrice = milk.price().multiply(BigDecimal.valueOf(2)).add(bread.price()).add(apple.price());

        cart.addProduct(milk);
        cart.addProduct(milk);
        cart.addProduct(bread);
        cart.addProduct(apple);

        var receiptGenerator = new ReceiptGenerator(Set.of());

        // When
        var receipt = receiptGenerator.generate(cart);

        // Then
        assertNotNull(receipt);
        assertEquals(3, receipt.entries().size());
        assertEquals(expectedTotalPrice, receipt.totalPrice());
        assertEquals(0, receipt.discounts().size());
    }

    @Test
    void givenBasketWithDiscount() {
        // Given
        var productDb = new ProductDb();
        var cart = new Basket();
        var cereals = productDb.getProduct("Cereals");
        var bread = productDb.getProduct("Bread");
        var steak = productDb.getProduct("Steak");
        var expectedTotalPriceAfterFirstDiscount = bread.price().multiply(BigDecimal.valueOf(2)).add(cereals.price()).add(steak.price()).multiply(BigDecimal.valueOf(0.85));
        var expectedTotalPriceAfterSecondDiscount = expectedTotalPriceAfterFirstDiscount.multiply(BigDecimal.valueOf(0.9));


        cart.addProduct(cereals);
        cart.addProduct(bread);
        cart.addProduct(bread);
        cart.addProduct(steak);

        var receiptGenerator = new ReceiptGenerator(Set.of(new FifteenPercentDiscount(), new TenPercentDiscount()));

        // When
        var receipt = receiptGenerator.generate(cart);

        // Then
        assertNotNull(receipt);
        assertEquals(3, receipt.entries().size());
        assertEquals(expectedTotalPriceAfterSecondDiscount, receipt.totalPrice());
        assertEquals(2, receipt.discounts().size());
    }
}
