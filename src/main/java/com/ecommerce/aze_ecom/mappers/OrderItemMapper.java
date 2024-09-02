package com.ecommerce.aze_ecom.mappers;


import com.ecommerce.aze_ecom.beans.OrderItem;
import com.ecommerce.aze_ecom.playload.OrderItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {

    @Autowired
    private ProductMapper productMapper;

    public OrderItemDTO toOrderItemDTO(OrderItem orderItem) {
        if (orderItem == null) {
            return null;
        }

        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setOrderItem(orderItem.getOrderItemId());
        orderItemDTO.setProductDTO(productMapper.toDto(orderItem.getProduct()));
        orderItemDTO.setQuantity(orderItem.getQuantity());
        orderItemDTO.setDiscount(orderItem.getDiscount());
        orderItemDTO.setOrderedProductPrice(orderItem.getOrderedProductPrice());

        return orderItemDTO;
    }

    public OrderItem toOrderItem(OrderItemDTO orderItemDTO) {
        if (orderItemDTO == null) {
            return null;
        }

        OrderItem orderItem = new OrderItem();
        orderItem.setOrderItemId(orderItemDTO.getOrderItem());
        orderItem.setProduct(productMapper.toEntity(orderItemDTO.getProductDTO()));
        orderItem.setQuantity(orderItemDTO.getQuantity());
        orderItem.setDiscount(orderItemDTO.getDiscount());
        orderItem.setOrderedProductPrice(orderItemDTO.getOrderedProductPrice());

        return orderItem;
    }
}

