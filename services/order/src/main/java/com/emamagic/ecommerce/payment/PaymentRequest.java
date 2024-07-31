package com.emamagic.ecommerce.payment;

import com.emamagic.ecommerce.customer.CustomerResponse;
import com.emamagic.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
    BigDecimal amount,
    PaymentMethod paymentMethod,
    Integer orderId,
    String orderReference,
    CustomerResponse customer
) {
}
