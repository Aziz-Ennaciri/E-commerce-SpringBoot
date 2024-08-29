package com.ecommerce.aze_ecom.service.Impl;

import com.ecommerce.aze_ecom.Util.AuthUtil;
import com.ecommerce.aze_ecom.beans.Cart;
import com.ecommerce.aze_ecom.beans.CartItem;
import com.ecommerce.aze_ecom.beans.Product;
import com.ecommerce.aze_ecom.exceptions.APIException;
import com.ecommerce.aze_ecom.exceptions.ResourceNotFoundException;
import com.ecommerce.aze_ecom.mappers.CartMapper;
import com.ecommerce.aze_ecom.mappers.ProductMapper;
import com.ecommerce.aze_ecom.playload.CartDTO;
import com.ecommerce.aze_ecom.playload.ProductDTO;
import com.ecommerce.aze_ecom.repositories.CartItemRepository;
import com.ecommerce.aze_ecom.repositories.CartRepository;
import com.ecommerce.aze_ecom.repositories.ProductRepository;
import com.ecommerce.aze_ecom.service.Interf.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private AuthUtil authUtil;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public CartDTO addProductToCart(Long productId, Integer quantity) {
        Cart cart = createCart();

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

        CartItem cartItem = cartItemRepository.findCartItemByProductIdAndCartId(cart.getCartId(), productId);

        if (cartItem != null) {
            throw new APIException("Product " + product.getProductName() + " already exists in the cart");
        }

        if (product.getQuantity() == 0) {
            throw new APIException(product.getProductName() + " is not available");
        }

        if (product.getQuantity() < quantity) {
            throw new APIException("Please, make an order of the " + product.getProductName()
                    + " less than or equal to the quantity " + product.getQuantity() + ".");
        }

        CartItem newCartItem = new CartItem();
        newCartItem.setProduct(product);
        newCartItem.setCart(cart);
        newCartItem.setQuantity(quantity);
        newCartItem.setDiscount(product.getDiscount());
        newCartItem.setProductPrice(product.getSpecialPrice());

        cartItemRepository.save(newCartItem);

        cart.setTotalPrice(cart.getTotalPrice() + (product.getSpecialPrice() * quantity));

        cartRepository.save(cart);

        // Use CartMapper to convert Cart to CartDTO
        return cartMapper.toCartDTO(cart);
    }

    @Override
    public List<CartDTO> getAllCarts() {
        List<Cart> carts = cartRepository.findAll();

        if (carts.isEmpty()){
            throw new APIException("No Cart exist");
        }
        List<CartDTO> cartDTOS = carts.stream().map(cartMapper::toCartDTO).toList();
        return cartDTOS;
    }

    @Override
    public CartDTO getCart(String emailId, Long cartId) {
        Cart cart = cartRepository.findCartByEmailAndCartId(emailId,cartId);
        if (cart == null){
            throw new ResourceNotFoundException("Cart","cardId",cartId);
        }
        CartDTO cartDTO = cartMapper.toCartDTO(cart);
        cart.getCartItems().forEach(
                c->c.getProduct().setQuantity(c.getQuantity()));
        List<ProductDTO> productDTOs = cart.getCartItems().stream()
                .map(cartItem -> productMapper.toDto(cartItem.getProduct()))
                .collect(Collectors.toList());

        cartDTO.setProductDTOS(productDTOs);

        return cartDTO;
    }

    private Cart createCart() {
        Cart userCart = cartRepository.findCartByEmail(authUtil.loggedInEmail());
        if (userCart != null) {
            return userCart;
        }

        Cart cart = new Cart();
        cart.setTotalPrice(0.00);
        cart.setUser(authUtil.loggedInUser());
        return cartRepository.save(cart);
    }
}
