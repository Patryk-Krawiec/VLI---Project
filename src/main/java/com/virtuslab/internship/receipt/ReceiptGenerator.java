package com.virtuslab.internship.receipt;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.product.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ReceiptGenerator {

//    public Receipt generate(Basket basket) {
//        List<ReceiptEntry> receiptEntries = new ArrayList<>();
//        var getprodlist = basket.getProducts();
//        var prodlist = getprodlist.stream()
//                .map(product -> new ReceiptEntry(product, 1))
//                .collect(Collectors.toList());
//        return new Receipt(prodlist);
//    }
    public Receipt generate(Basket basket) {
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        var getprodlist = basket.getProducts();
        Map<Product, Long> collect = getprodlist.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        collect.forEach((product, aLong) -> {
            receiptEntries.add(new ReceiptEntry(product, aLong.intValue()));
        });
        return new Receipt(receiptEntries);
    }
}