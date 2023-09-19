package com.sabancidx.productapi.repository;

import com.sabancidx.productapi.entity.CurrencyEnum;
import com.sabancidx.productapi.entity.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {

        Product product1 = new Product(1,"Product1", 1111,"testDesc1", "Brand1", CurrencyEnum.EUR, 17.2, true, false);
        Product product2 = new Product(2,"Product2", 1112,"testDesc2", "Brand2", CurrencyEnum.TL, 11.2, true, false);
        Product product3 = new Product(3,"Product3", 1113,"testDesc3", "Brand3", CurrencyEnum.JPY, 13.5, true, false);

        productRepository.saveAll(List.of(product1, product2, product3));
    }

    @Test
    public void testFindByName() {
        String nameToFind = "Product1";
        Product foundProduct = productRepository.findByName(nameToFind);
        assertNotNull(foundProduct);
        assertEquals(nameToFind, foundProduct.getName());
        assertFalse(foundProduct.isDeleted());
    }

    @Test
    public void testFindByBrand() {
        String brandToFind = "Brand2";
        List<Product> foundProducts = productRepository.findByBrand(brandToFind);
        assertNotNull(foundProducts);
        assertFalse(foundProducts.isEmpty());
        for (Product product : foundProducts) {
            assertEquals(brandToFind, product.getBrand());
            assertFalse(product.isDeleted());
        }
    }

    @Test
    public void testFindByCode() {
        int codeToFind = 1111;
        Product foundProduct = productRepository.findByCode(codeToFind);
        assertNotNull(foundProduct);
        assertEquals(codeToFind, foundProduct.getCode());
        assertFalse(foundProduct.isDeleted());
    }

    @Test
    public void testFindByPriceBetween() {
        double minPrice = 13;
        double maxPrice = 15;
        List<Product> foundProducts = productRepository.findByPriceBetween(minPrice, maxPrice);
        assertNotNull(foundProducts);
        assertFalse(foundProducts.isEmpty());
        for (Product product : foundProducts) {
            double price = product.getPrice();
            assertTrue(price >= minPrice && price <= maxPrice);
            assertFalse(product.isDeleted());
        }
    }

    @Test
    public void testFindAll() {
        List<Product> allProducts = productRepository.findAll();
        assertNotNull(allProducts);
        assertFalse(allProducts.isEmpty());
        for (Product product : allProducts) {
            assertFalse(product.isDeleted());
        }
    }
}