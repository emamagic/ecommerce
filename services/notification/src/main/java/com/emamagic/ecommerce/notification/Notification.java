package com.emamagic.ecommerce.notification;

import com.emamagic.ecommerce.kafka.order.OrderConfirmation;
import com.emamagic.ecommerce.kafka.payment.PaymentConfirmation;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Builder
@Data
@Document
public class Notification {
    @Id
    private String id;
    private NotificationType type;
    private LocalDateTime notificationDate;
    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentConfirmation;
}
