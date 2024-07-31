package com.emamagic.ecommerce.payment;

import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {

    public Payment toPayment(PaymentRequest req) {
        return Payment.builder()
                .id(req.id())
                .orderId(req.orderId())
                .paymentMethod(req.paymentMethod())
                .amount(req.amount())
                .build();
    }
}
