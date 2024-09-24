package com.ecommerce.aze_ecom.mappers;

import com.ecommerce.aze_ecom.beans.CartItem;
import com.ecommerce.aze_ecom.DTOs.CartItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemMapper {

    @Autowired
    private  CartMapper cartMapper;
    @Autowired
    private  ProductMapper productMapper;


    public CartItemDTO toDto(CartItem cartItem) {
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setCartItemId(cartItem.getCartItemId());
        cartItemDTO.setCartDTO(cartMapper.toCartDTO(cartItem.getCart()));
        cartItemDTO.setProductDTO(productMapper.toDto(cartItem.getProduct()));
        cartItemDTO.setQuantity(cartItem.getQuantity());
        cartItemDTO.setDiscount(cartItem.getDiscount());
        cartItemDTO.setProductPrice(cartItem.getProductPrice());
        return cartItemDTO;
    }

    public CartItem toEntity(CartItemDTO cartItemDTO) {
        CartItem cartItem = new CartItem();
        cartItem.setCartItemId(cartItemDTO.getCartItemId());
        cartItem.setQuantity(cartItemDTO.getQuantity());
        cartItem.setDiscount(cartItemDTO.getDiscount());
        cartItem.setProductPrice(cartItemDTO.getProductPrice());
        return cartItem;
    }
}
