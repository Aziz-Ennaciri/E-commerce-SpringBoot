package com.ecommerce.aze_ecom.service.Interf;

import com.ecommerce.aze_ecom.beans.Product;
import com.ecommerce.aze_ecom.playload.ProductDTO;

public interface ProductService {
    ProductDTO addProduct(Long categoryId, Product product);
}
