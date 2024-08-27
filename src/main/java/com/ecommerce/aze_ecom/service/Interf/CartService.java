package com.ecommerce.aze_ecom.service.Interf;

import com.ecommerce.aze_ecom.playload.CartDTO;

public interface CartService {
    CartDTO addProductToCart(Long productId, Integer quantity);
}
