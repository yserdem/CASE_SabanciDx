package com.sabancidx.productapi.repository;

import com.sabancidx.productapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE p.name = :name")
    Product findByName(String name);
    @Query("SELECT p FROM Product p WHERE p.brand = :brand")
    List<Product> findByBrand(String brand);
    @Query("SELECT p FROM Product p WHERE p.code = :code")
    Product findByCode(int code);
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :minprice AND :maxprice")
    List<Product> findByPriceBetween(@Param("minprice") double minPrice, @Param("maxprice") double maxPrice);

}
