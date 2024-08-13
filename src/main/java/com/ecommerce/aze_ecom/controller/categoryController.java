package com.ecommerce.aze_ecom.controller;

import com.ecommerce.aze_ecom.beans.Category;
import com.ecommerce.aze_ecom.playload.CategoryDTO;
import com.ecommerce.aze_ecom.playload.CategoryResponse;
import com.ecommerce.aze_ecom.service.Impl.CategoryServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class categoryController {

    @Autowired
    private CategoryServiceImpl categoryService;


    @GetMapping("/api/public/categories"    )
    public ResponseEntity<CategoryResponse> getAllCategories(){
        CategoryResponse categoryResponse = categoryService.getAllCategories();
        return new ResponseEntity<>(categoryResponse,HttpStatus.OK);
    }

    @PostMapping("/api/public/categories")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
//        categoryService.createCategory(category);
        return new ResponseEntity<>(categoryService.createCategory(categoryDTO),HttpStatus.CREATED);
    }

    @PutMapping("/api/public/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@RequestBody Category category,@PathVariable Long categoryId){
            Category savedCategory = categoryService.updateCategory(category,categoryId);
            return new ResponseEntity<>("category with id"+categoryId, HttpStatus.OK);
    }

    @DeleteMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId){
        String status = categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(status, HttpStatus.OK);
        }
}
