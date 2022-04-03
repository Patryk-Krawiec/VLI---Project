package com.virtuslab.internship.Products.example.rest;

import com.virtuslab.internship.Products.example.basket.Basket;
import com.virtuslab.internship.Products.example.product.Product;
import com.virtuslab.internship.Products.example.product.ProductDb;
import com.virtuslab.internship.Products.example.receipt.Receipt;
import com.virtuslab.internship.Products.example.receipt.ReceiptGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/receipt")
public class ReceiptRest {
    private ReceiptGenerator receiptGenerator;
    private ProductDb productDb;

    @Autowired
    public ReceiptRest(ReceiptGenerator receiptGenerator, ProductDb productDb) {
        this.receiptGenerator = receiptGenerator;
        this.productDb = productDb;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Receipt calculateBasket(@RequestBody Basket basket) {
        List<Product> collect = basket.getProducts().stream().map(product -> productDb.getProduct(product.name())).collect(Collectors.toList());
        return receiptGenerator.generate(new Basket(collect));
    }
}
