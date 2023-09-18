package com.sabancidx.productapi.service;

import com.sabancidx.productapi.entity.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(Product product);
    List<Product> findAll();
    Product findByName();
    Product findByCode();
    List<Product> findByBrand();
    List<Product> findByPriceRange();
    Product updateProduct(Product product);
    void deleteProduct(int id);

}
