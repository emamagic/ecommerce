package com.emamagic.ecommerce.category;

import com.emamagic.ecommerce.product.Product;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@Entity
public class Category {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<Product> products;
}
