package org.raghoul818.glovohillel.model.order;

import lombok.*;
import jakarta.persistence.*;
import org.raghoul818.glovohillel.model.product.Product;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    private Integer id;
    private LocalDate date;
    private Double cost;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> products;
}

