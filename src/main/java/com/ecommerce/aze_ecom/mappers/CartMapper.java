package com.ecommerce.aze_ecom.mappers;

import com.ecommerce.aze_ecom.beans.Cart;
import com.ecommerce.aze_ecom.beans.CartItem;
import com.ecommerce.aze_ecom.playload.CartDTO;
import com.ecommerce.aze_ecom.playload.ProductDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapper {

    public CartDTO toCartDTO(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCartId(cart.getCartId());
        cartDTO.setTotalPrice(cart.getTotalPrice());

        // Convert List<CartItem> to List<ProductDTO>
        List<ProductDTO> productDTOS = cart.getCartItems().stream()
                .map(this::toProductDTO)
                .collect(Collectors.toList());

        cartDTO.setProductDTOS(productDTOS);
        return cartDTO;
    }

    private ProductDTO toProductDTO(CartItem cartItem) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(cartItem.getProduct().getProductId());
        productDTO.setProductName(cartItem.getProduct().getProductName());
        productDTO.setImage(cartItem.getProduct().getImage());
        productDTO.setQuantity(cartItem.getQuantity()); // Set quantity from CartItem
        productDTO.setPrice(cartItem.getProduct().getPrice());
        productDTO.setDiscount(cartItem.getDiscount());
        productDTO.setSpecialPrice(cartItem.getProductPrice()); // Use productPrice from CartItem

        return productDTO;
    }
}
