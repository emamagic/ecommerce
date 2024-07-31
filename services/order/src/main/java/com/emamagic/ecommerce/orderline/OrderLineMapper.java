package com.emamagic.ecommerce.orderline;

import com.emamagic.ecommerce.order.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {

    public OrderLine toOrderLine(OrderLineRequest req) {
        return OrderLine.builder()
                .id(req.id())
                .quantity(req.quantity())
                .order(Order.builder().id(req.orderId()).build())
                .productId(req.productId())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(orderLine.getId(), orderLine.getQuantity());
    }
}
