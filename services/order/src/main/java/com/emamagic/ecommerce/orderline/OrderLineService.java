package com.emamagic.ecommerce.orderline;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderLineService {

    private final OrderLineRepository repo;
    private final OrderLineMapper mapper;

    public Integer saveOrderLine(OrderLineRequest req) {
        var order = mapper.toOrderLine(req);
        return repo.save(order).getId();
    }

    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
        return repo.findAllByOrderId(orderId).stream()
                .map(mapper::toOrderLineResponse)
                .toList();
    }
}
