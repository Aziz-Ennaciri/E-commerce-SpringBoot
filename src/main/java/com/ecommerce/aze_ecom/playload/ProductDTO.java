package com.ecommerce.aze_ecom.playload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {
    private Long productId;
    private String productName;
    private String image;
    private String description; // Ensure this matches your Product entity
    private Integer quantity;
    private double price;
    private double discount;
    private double specialPrice;
}
