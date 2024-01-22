package org.raghoul818.glovohillel.dto.order;

import lombok.*;
import org.raghoul818.glovohillel.dto.product.ProductDto;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
    private Integer id;
    private String date;
    private Double cost;
    private List<ProductDto> products;
}
