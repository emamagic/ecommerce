package com.emamagic.ecommerce.order;

import com.emamagic.ecommerce.customer.CustomerClient;
import com.emamagic.ecommerce.exception.BusinessException;
import com.emamagic.ecommerce.kafka.OrderConfirmation;
import com.emamagic.ecommerce.kafka.OrderProducer;
import com.emamagic.ecommerce.orderline.OrderLineRequest;
import com.emamagic.ecommerce.orderline.OrderLineService;
import com.emamagic.ecommerce.payment.PaymentClient;
import com.emamagic.ecommerce.payment.PaymentRequest;
import com.emamagic.ecommerce.product.ProductClient;
import com.emamagic.ecommerce.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository repo;
    private final OrderMapper mapper;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final PaymentClient paymentClient;
    private final OrderLineService orderLineSvc;
    private final OrderProducer orderProducer;

    public Integer createOrder(OrderRequest req) {
        var customer = customerClient.findCustomerById(req.customerId())
                .orElseThrow(() -> new BusinessException(String.format("Cannot create order:: No Customer exists with the provided ID:: %s", customerClient)));

        var purchaseProducts = productClient.purchaseProducts(req.products());
        var order = repo.save(mapper.toOrder(req));

        for (PurchaseRequest purchaseRequest : req.products()) {
            orderLineSvc.saveOrderLine(new OrderLineRequest(null, order.getId(), purchaseRequest.productId(), purchaseRequest.quantity()));
        }

        var paymentRequest = new PaymentRequest(
                req.amount(),
                req.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );

        paymentClient.requestOrderPayment(paymentRequest);
        orderProducer.sendOrderConfirmation(new OrderConfirmation(
                req.reference(),
                req.amount(),
                req.paymentMethod(),
                customer,
                purchaseProducts
        ));

        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return repo.findAll().stream().map(mapper::fromOrder).toList();
    }


    public OrderResponse findById(Integer orderId) {
        return repo.findById(orderId).map(mapper::fromOrder).orElseThrow(() ->
                new EntityNotFoundException(String.format("No order found with the provided ID:: %d", orderId)));
    }
}
