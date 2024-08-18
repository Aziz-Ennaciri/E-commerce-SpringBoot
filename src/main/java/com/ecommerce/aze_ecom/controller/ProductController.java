package com.ecommerce.aze_ecom.controller;

import com.ecommerce.aze_ecom.beans.Product;
import com.ecommerce.aze_ecom.playload.ProductDTO;
import com.ecommerce.aze_ecom.playload.ProductResponse;
import com.ecommerce.aze_ecom.service.Interf.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("public/products")
    public ResponseEntity<ProductResponse> getAllProducts(){
        ProductResponse productResponse = productService.getAllProducts();
        return new ResponseEntity<>(productResponse,HttpStatus.OK);
    }

    @GetMapping("public/categories/{categoryId}/products")
    public ResponseEntity<ProductResponse> getProductsByCategory(@PathVariable Long categoryId){
        ProductResponse productResponse =productService.searchByCategory(categoryId);
        return new ResponseEntity<>(productResponse,HttpStatus.OK) ;
    }

    @PostMapping("admin/categories/{categoryId}/product")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody Product product , @PathVariable Long categoryId){
        ProductDTO productDTO=productService.addProduct(categoryId,product);
        return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
    }
}
