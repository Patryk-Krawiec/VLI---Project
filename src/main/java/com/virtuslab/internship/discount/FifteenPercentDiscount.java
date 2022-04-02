package com.virtuslab.internship.discount;

import com.virtuslab.internship.receipt.Receipt;

import java.math.BigDecimal;
import java.util.stream.Collectors;

public class FifteenPercentDiscount {

    public static String NAME = "FifteenPercentDiscount";

    public Receipt apply(Receipt receipt) {
        if (shouldApply15(receipt)) {
            var totalPrice = receipt.totalPrice().multiply(BigDecimal.valueOf(0.85));
            var discounts = receipt.discounts();
            discounts.add(NAME);
            return new Receipt(receipt.entries(), discounts, totalPrice);
        }
        return receipt;
    }

    private boolean shouldApply15(Receipt receipt) {
        var allGrainItems = (Integer) receipt.entries().stream()
                .filter(receiptEntry -> receiptEntry.product().type().name().equals("GRAINS"))
                .map(receiptEntry -> receiptEntry.quantity().intValue()).mapToInt(value -> value).sum();
        return allGrainItems >= 3;
    }
}
