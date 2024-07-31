package com.emamagic.ecommerce.order;

import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    public Order toOrder(OrderRequest req) {
        return Order.builder()
                .id(req.id())
                .customerId(req.customerId())
                .reference(req.reference())
                .totalAmount(req.amount())
                .paymentMethod(req.paymentMethod())
                .build();
    }

    public OrderResponse fromOrder(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId()
        );
    }
}
