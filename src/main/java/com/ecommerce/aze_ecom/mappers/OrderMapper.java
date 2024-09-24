package com.ecommerce.aze_ecom.mappers;

import com.ecommerce.aze_ecom.beans.Address;
import com.ecommerce.aze_ecom.beans.Order;
import com.ecommerce.aze_ecom.DTOs.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderMapper {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private PaymentMapper paymentMapper;

    @Autowired
    private AddressMapper addressMapper;

    public OrderDTO toOrderDTO(Order order) {
        if (order == null) {
            return null;
        }

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setEmail(order.getEmail());
        orderDTO.setOrderItems(order.getOrderItems().stream()
                .map(orderItemMapper::toOrderItemDTO)
                .collect(Collectors.toList()));
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setPayment(paymentMapper.toPaymentDTO(order.getPayment()));
        orderDTO.setTotalAmount(order.getTotalAmount());
        orderDTO.setOrderStatus(order.getOrderStatus());
        orderDTO.setAddressId(order.getAddress().getAddressId());

        return orderDTO;
    }

    public Order toOrder(OrderDTO orderDTO) {
        if (orderDTO == null) {
            return null;
        }

        Order order = new Order();
        order.setOrderId(orderDTO.getOrderId());
        order.setEmail(orderDTO.getEmail());
        order.setOrderItems(orderDTO.getOrderItems().stream()
                .map(orderItemMapper::toOrderItem)
                .collect(Collectors.toList()));
        order.setOrderDate(orderDTO.getOrderDate());
        order.setPayment(paymentMapper.toPayment(orderDTO.getPayment()));
        order.setTotalAmount(orderDTO.getTotalAmount());
        order.setOrderStatus(orderDTO.getOrderStatus());

        // Create a new Address entity with just the ID
        Address address = new Address();
        address.setAddressId(orderDTO.getAddressId());
        order.setAddress(address);

        return order;
    }
}

