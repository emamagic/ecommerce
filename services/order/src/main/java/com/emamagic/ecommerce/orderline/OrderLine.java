package com.emamagic.ecommerce.orderline;

import com.emamagic.ecommerce.order.Order;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Entity
public class OrderLine {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    // it is not mandatory here
    @JoinColumn(name = "order_id")
    private Order order;
    private Integer productId;
    private double quantity;
}
