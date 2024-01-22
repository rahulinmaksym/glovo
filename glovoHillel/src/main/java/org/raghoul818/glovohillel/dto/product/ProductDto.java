package org.raghoul818.glovohillel.dto.product;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    private Integer id;
    private String name;
    private Double cost;
}
