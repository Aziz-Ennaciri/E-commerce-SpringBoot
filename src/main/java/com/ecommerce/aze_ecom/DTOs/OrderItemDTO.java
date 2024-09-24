package com.ecommerce.aze_ecom.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {
    private Long orderItem;
    private ProductDTO productDTO;
    private Integer quantity;
    private double discount;
    private double orderedProductPrice;
}
