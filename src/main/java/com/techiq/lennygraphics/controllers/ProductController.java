package com.techiq.lennygraphics.controllers;

import com.techiq.lennygraphics.services.ProductService;
import com.techiq.lennygraphics.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/lennygraphics/designs")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> register() {
        return productService.getAllProducts();
    }
}

