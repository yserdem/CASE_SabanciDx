package com.sabancidx.productapi.controller;

import com.sabancidx.productapi.dto.PriceRangeRequest;
import com.sabancidx.productapi.entity.Product;
import com.sabancidx.productapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping("/")
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/name/{name}")
    public Product getProductByName(@PathVariable String name) {
        return productService.findByName(name);
    }

    @GetMapping("/code/{code}")
    public Product getProductByCode(@PathVariable int code) {
        return productService.findByCode(code);
    }

    @GetMapping("/brand/{brand}")
    public List<Product> getProductsByBrand(@PathVariable String brand) {
        return productService.findByBrand(brand);
    }

    @PostMapping("/findByPriceRange")
    public List<Product> getProductsByPriceRange(@RequestBody PriceRangeRequest priceRangeRequest) {
        return productService.findByPriceRange(priceRangeRequest.getMinprice(), priceRangeRequest.getMaxprice());
    }

    @PutMapping("/update/{name}")
    public Product updateProduct(@PathVariable String name, @RequestBody Product product) {
        Product newProduct = new Product();
        return productService.findByName(name).;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
    }
}
