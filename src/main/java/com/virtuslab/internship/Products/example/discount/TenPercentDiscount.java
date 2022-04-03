package com.virtuslab.internship.Products.example.discount;

import com.virtuslab.internship.Products.example.receipt.Receipt;

import java.math.BigDecimal;

public class TenPercentDiscount implements DiscountPolicy{

    public static String NAME = "TenPercentDiscount";

    @Override
    public Receipt apply(Receipt receipt) {
        if (shouldApply(receipt)) {
            var totalPrice = receipt.totalPrice().multiply(BigDecimal.valueOf(0.9));
            var discounts = receipt.discounts();
            discounts.add(NAME);
            return new Receipt(receipt.entries(), discounts, totalPrice);
        }
        return receipt;
    }

    @Override
    public boolean shouldApply(Receipt receipt) {
        return receipt.totalPrice().compareTo(BigDecimal.valueOf(50)) >= 0;
    }

    @Override
    public int getPriority() {
        return 1;
    }
}
