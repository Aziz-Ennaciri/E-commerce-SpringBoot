package com.ecommerce.aze_ecom.service.Interf;

import com.ecommerce.aze_ecom.playload.CartDTO;

import java.util.List;

public interface CartService {
    CartDTO addProductToCart(Long productId, Integer quantity);

    List<CartDTO> getAllCarts();

    CartDTO getCart(String emailId, Long cartId);
}
