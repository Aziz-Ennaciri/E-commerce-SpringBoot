package com.ecommerce.aze_ecom.mappers;

import com.ecommerce.aze_ecom.beans.Cart;
import com.ecommerce.aze_ecom.beans.CartItem;
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
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCartId(cart.getCartId());
        cartDTO.setTotalPrice(cart.getTotalPrice());

        // Map the list of CartItems to ProductDTOs
        List<ProductDTO> productDTOs = cart.getCartItems().stream()
                .map(cartItem -> {
                    ProductDTO productDTO = productMapper.toDto(cartItem.getProduct());
                    productDTO.setQuantity(cartItem.getQuantity()); // Set the quantity from CartItem
                    return productDTO;
                })
                .collect(Collectors.toList());

        cartDTO.setProductDTOS(productDTOs);
        return cartDTO;
    }

    public Cart toCart(CartDTO cartDTO) {
        Cart cart = new Cart();
        cart.setCartId(cartDTO.getCartId());
        cart.setTotalPrice(cartDTO.getTotalPrice());

        // Map the list of ProductDTOs back to CartItems
        List<CartItem> cartItems = cartDTO.getProductDTOS().stream()
                .map(productDTO -> {
                    CartItem cartItem = new CartItem();
                    cartItem.setProduct(productMapper.toEntity(productDTO));
                    cartItem.setQuantity(productDTO.getQuantity()); // Set the quantity from ProductDTO
                    cartItem.setCart(cart); // Ensure the relationship is set
                    return cartItem;
                })
                .collect(Collectors.toList());

        cart.setCartItems(cartItems);

        return cart;
    }
}
