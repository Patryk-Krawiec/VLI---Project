package com.virtuslab.internship.receipt;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.discount.DiscountPolicy;
import com.virtuslab.internship.product.Product;

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
        Map<Product, Long> collect = getprodlist.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        collect.forEach((product, aLong) -> {
            receiptEntries.add(new ReceiptEntry(product, aLong.intValue()));
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