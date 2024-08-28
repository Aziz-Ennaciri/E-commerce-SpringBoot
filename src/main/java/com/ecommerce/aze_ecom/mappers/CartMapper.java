package com.ecommerce.aze_ecom.mappers;

import com.ecommerce.aze_ecom.beans.Cart;
import com.ecommerce.aze_ecom.beans.CartItem;
import com.ecommerce.aze_ecom.mappers.ProductMapper;
import com.ecommerce.aze_ecom.playload.CartDTO;
import com.ecommerce.aze_ecom.playload.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapper {

    @Autowired
    private ProductMapper productMapper;

    public CartDTO toCartDTO(Cart cart) {
        if (cart == null) {
            return null;
        }

        CartDTO cartDTO = new CartDTO();
        cartDTO.setCartId(cart.getCartId());
        cartDTO.setTotalPrice(cart.getTotalPrice());

        List<ProductDTO> productDTOs = cart.getCartItems().stream()
                .map(cartItem -> productMapper.toDto(cartItem.getProduct()))
                .collect(Collectors.toList());

        cartDTO.setProductDTOS(productDTOs);
        return cartDTO;
    }


    public Cart toCart(CartDTO cartDTO) {
        if (cartDTO == null) {
            return null;
        }

        Cart cart = new Cart();
        cart.setCartId(cartDTO.getCartId());
        cart.setTotalPrice(cartDTO.getTotalPrice());


        return cart;
    }
}
