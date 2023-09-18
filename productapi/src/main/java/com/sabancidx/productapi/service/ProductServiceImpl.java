package com.sabancidx.productapi.service;

import com.sabancidx.productapi.entity.Product;
import com.sabancidx.productapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findByName() {
        return null;
    }

    @Override
    public Product findByCode() {
        return null;
    }

    @Override
    public List<Product> findByBrand() {
        return null;
    }

    @Override
    public List<Product> findByPriceRange() {
        return null;
    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }

    @Override
    public void deleteProduct(int id) {

    }
}
