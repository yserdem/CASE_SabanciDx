package com.sabancidx.productapi.service;

import com.sabancidx.productapi.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product addProduct(Product product);
    List<Product> findAll();
    Product findByName(String name);

    Product findByCode(int code);

    List<Product> findByBrand(String brand);

    List<Product> findByPriceRange(double minPrice, double maxPrice);

    Product updateProduct(Product product);
    void deleteProduct(int id);

}
