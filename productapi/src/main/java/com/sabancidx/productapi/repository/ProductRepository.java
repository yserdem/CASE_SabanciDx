package com.sabancidx.productapi.repository;

import com.sabancidx.productapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Integer> {

}
