package com.sabancidx.productapi.controller;

import com.sabancidx.productapi.entity.CurrencyEnum;
import com.sabancidx.productapi.entity.Product;
import com.sabancidx.productapi.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductService productService;

    @BeforeEach
    public void setup() {
        productService.deleteAll(); // Clear data before each test
    }

    @Test
    public void testGetAllProducts() throws Exception {
        productService.addProduct(new Product(1,"Product1", 1111,"testDesc1", "Brand1", CurrencyEnum.EUR, 17.2, true, false));
        productService.addProduct(new Product(2,"Product2", 1111,"testDesc1", "Brand1", CurrencyEnum.EUR, 17.2, true, false));

        mockMvc.perform(MockMvcRequestBuilders.get("/products/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Product1"))
                .andExpect(jsonPath("$[1].name").value("Product2"));
    }
}

