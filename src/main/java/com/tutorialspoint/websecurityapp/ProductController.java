package com.tutorialspoint.websecurityapp;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    @GetMapping
    @PreAuthorize("hasRole('ROLE_SYSTEMADMIN')")
    public String getProductName() {
        return "Honey";
    }
}
