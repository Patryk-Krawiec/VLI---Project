package com.virtuslab.internship.Products.example.rest;

import com.virtuslab.internship.Products.example.discount.DiscountPolicy;
import com.virtuslab.internship.Products.example.discount.FifteenPercentDiscount;
import com.virtuslab.internship.Products.example.discount.TenPercentDiscount;
import com.virtuslab.internship.Products.example.product.ProductDb;
import com.virtuslab.internship.Products.example.receipt.ReceiptGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class Config {
    @Bean
    public ReceiptGenerator receiptGenerator(Set<DiscountPolicy> discountPolicies) {
        return new ReceiptGenerator(discountPolicies);
    }

    @Bean
    public Set<DiscountPolicy> discountPolicies() {
        return Set.of(new FifteenPercentDiscount(), new TenPercentDiscount());
    }

    @Bean
    public ProductDb productDb() {
        return new ProductDb();
    }
}
