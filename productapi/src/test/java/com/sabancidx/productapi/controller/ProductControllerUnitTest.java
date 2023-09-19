package com.sabancidx.productapi.controller;

import com.sabancidx.productapi.entity.CurrencyEnum;
import com.sabancidx.productapi.entity.Product;
import com.sabancidx.productapi.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ProductControllerUnitTest {

    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new ProductController(productService)).build();
    }

    @Test
    public void testGetAllProducts() throws Exception {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1,"Product1", 1111,"testDesc1", "Brand1", CurrencyEnum.EUR, 17.2, true, false));

        when(productService.findAll()).thenReturn(productList);

        mockMvc.perform(get("/products/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Product1"));
    }

}
