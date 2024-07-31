package com.emamagic.ecommerce.payment;

import com.emamagic.ecommerce.notification.NotificationProducer;
import com.emamagic.ecommerce.notification.PaymentNotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentService {

    private final PaymentRepository repo;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;

    public Integer createPayment(PaymentRequest req) {
        var payment = repo.save(mapper.toPayment(req));
        notificationProducer.sendNotification(new PaymentNotificationRequest(
                req.orderReference(),
                req.amount(),
                req.paymentMethod(),
                req.customer().firstname(),
                req.customer().lastname(),
                req.customer().email()
        ));

        return payment.getId();
    }
}
