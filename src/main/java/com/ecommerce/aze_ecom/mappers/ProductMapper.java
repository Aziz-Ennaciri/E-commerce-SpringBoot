package com.ecommerce.aze_ecom.mappers;

import com.ecommerce.aze_ecom.beans.Product;
import com.ecommerce.aze_ecom.playload.ProductDTO;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public Product toEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setProductId(productDTO.getProductId());
        product.setProductName(productDTO.getProductName());
        product.setImage(productDTO.getImage());
        product.setDescription(productDTO.getDescription());
        product.setQuantity(productDTO.getQuantity());
        product.setPrice(productDTO.getPrice());
        product.setDiscount(productDTO.getDiscount());
        product.setSpecialPrice(productDTO.getSpecialPrice());
        return product;
    }

    public ProductDTO toDto(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(product.getProductId());
        productDTO.setProductName(product.getProductName());
        productDTO.setImage(product.getImage());
        productDTO.setDescription(product.getDescription());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setPrice(product.getPrice());
        productDTO.setDiscount(product.getDiscount());
        productDTO.setSpecialPrice(product.getSpecialPrice());
        return productDTO;
    }
}
