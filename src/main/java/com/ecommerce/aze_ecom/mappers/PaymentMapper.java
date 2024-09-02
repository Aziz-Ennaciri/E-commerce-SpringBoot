package com.ecommerce.aze_ecom.mappers;

import com.ecommerce.aze_ecom.beans.Payment;
import com.ecommerce.aze_ecom.playload.PaymentDTO;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public PaymentDTO toPaymentDTO(Payment payment) {
        if (payment == null) {
            return null;
        }

        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setPaymentId(payment.getPaymentId());
        paymentDTO.setPaymentMethod(payment.getPaymentMethod());
        paymentDTO.setPgPaymentId(payment.getPgPaymentId());
        paymentDTO.setPgStatus(payment.getPgStatus());
        paymentDTO.setPgResponseMessage(payment.getPgResponseMessage());
        paymentDTO.setPgName(payment.getPgName());

        return paymentDTO;
    }

    public Payment toPayment(PaymentDTO paymentDTO) {
        if (paymentDTO == null) {
            return null;
        }

        Payment payment = new Payment();
        payment.setPaymentId(paymentDTO.getPaymentId());
        payment.setPaymentMethod(paymentDTO.getPaymentMethod());
        payment.setPgPaymentId(paymentDTO.getPgPaymentId());
        payment.setPgStatus(paymentDTO.getPgStatus());
        payment.setPgResponseMessage(paymentDTO.getPgResponseMessage());
        payment.setPgName(paymentDTO.getPgName());

        return payment;
    }
}

