package com.emamagic.ecommerce.kafka;

import com.emamagic.ecommerce.customer.CustomerResponse;
import com.emamagic.ecommerce.order.PaymentMethod;
import com.emamagic.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
