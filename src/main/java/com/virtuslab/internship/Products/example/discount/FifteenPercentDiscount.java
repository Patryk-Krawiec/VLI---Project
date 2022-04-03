package com.virtuslab.internship.Products.example.discount;

import com.virtuslab.internship.Products.example.receipt.Receipt;

import java.math.BigDecimal;

public class FifteenPercentDiscount implements DiscountPolicy{

    public static String NAME = "FifteenPercentDiscount";

    @Override
    public Receipt apply(Receipt receipt) {
        if (shouldApply(receipt)) {
            var totalPrice = receipt.totalPrice().multiply(BigDecimal.valueOf(0.85));
            var discounts = receipt.discounts();
            discounts.add(NAME);
            return new Receipt(receipt.entries(), discounts, totalPrice);
        }
        return receipt;
    }

    @Override
    public boolean shouldApply(Receipt receipt) {
        var allGrainItems = (Integer) receipt.entries().stream()
                .filter(receiptEntry -> receiptEntry.product().type().name().equals("GRAINS"))
                .map(receiptEntry -> receiptEntry.quantity().intValue()).mapToInt(value -> value).sum();
        return allGrainItems >= 3;
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
