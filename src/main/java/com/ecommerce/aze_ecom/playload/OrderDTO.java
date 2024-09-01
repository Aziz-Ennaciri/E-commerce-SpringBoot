package com.ecommerce.aze_ecom.playload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long orderId;
    public String email;
    private List<OrderItemDTO> orderItemDTOS;
    private LocalDate orderDate;
    private PaymentDTO paymentDTO;
    private Double totalAmount;
    private String orderStatus;
    private Long addressId;
}
