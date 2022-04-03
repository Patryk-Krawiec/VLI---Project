package com.virtuslab.internship.Products.example.discount;

import com.virtuslab.internship.Products.example.receipt.Receipt;

public interface DiscountPolicy {
    Receipt apply(Receipt receipt);
    boolean shouldApply(Receipt receipt);
    int getPriority();
}
