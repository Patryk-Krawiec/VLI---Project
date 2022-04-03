package com.virtuslab.internship.Products.example.receipt;

import com.virtuslab.internship.Products.example.basket.Basket;
import com.virtuslab.internship.Products.example.discount.DiscountPolicy;
import com.virtuslab.internship.Products.example.product.Product;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ReceiptGenerator {

    private Set<DiscountPolicy> discounts;

    public ReceiptGenerator(Set<DiscountPolicy> discounts) {
        this.discounts = discounts;
    }

    public Receipt generate(Basket basket) {
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        var getprodlist = basket.getProducts();
        Map<Product, Long> collect = getprodlist.stream().filter(Objects::nonNull).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        collect.forEach((product, quantity) -> {
            receiptEntries.add(new ReceiptEntry(product, quantity.intValue()));
        });
        Receipt receipt = new Receipt(receiptEntries);
        List<DiscountPolicy> activeDiscount = discounts.stream()
                .filter(discountPolicy -> discountPolicy.shouldApply(receipt))
                .collect(Collectors.toList());

        activeDiscount.sort(Comparator.comparing(DiscountPolicy::getPriority));
        Receipt res = receipt;
        for (DiscountPolicy discountPolicy : activeDiscount) {
            res = discountPolicy.apply(res);
        }
        return res;
    }
}