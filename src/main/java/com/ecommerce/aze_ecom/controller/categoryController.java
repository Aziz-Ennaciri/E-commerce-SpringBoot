package com.ecommerce.aze_ecom.controller;

import com.ecommerce.aze_ecom.beans.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class categoryController {
    private List<Category> categories = new ArrayList<>();

    @GetMapping("/api/public/categories")
    public List<Category> getAllCategories(){
        return categories;
    }

    @PostMapping("/api/public/categories")
    public String createCategory(@RequestBody Category category){
        categories.add(category);
        return "Category have been created";
    }
}
