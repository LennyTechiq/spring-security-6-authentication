package com.techiq.lennygraphics.controllers;

import com.techiq.lennygraphics.auth.AuthenticationResponse;
import com.techiq.lennygraphics.auth.RegisterRequest;
import com.techiq.lennygraphics.models.Product;
import com.techiq.lennygraphics.models.User;
import com.techiq.lennygraphics.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController("/lennygraphics")
public class MainController {

    private ProductService productService;

    @Autowired
    public MainController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public String getHome(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping("/admin")
    public String getAdmindashboard(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "/admin/admin";
    }

    @GetMapping("/admin/designs/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "/admin/admin-design-new";
    }

    @PostMapping("/admin/designs/add")
    public String addProduct(@RequestParam("category") String category,
                             @RequestParam("description") String description,
                             @RequestParam("image") MultipartFile multipartFile,
                             @RequestParam("name") String name
    ) throws IOException {
        productService.saveProduct(category, description, multipartFile, name);

        return "redirect:/admin";
    }
}