package com.virtuslab.internship.discount;

import com.virtuslab.internship.receipt.Receipt;

public interface DiscountPolicy {

    Receipt apply(Receipt receipt);
    boolean shouldApply(Receipt receipt);
    int getPriority();
}
