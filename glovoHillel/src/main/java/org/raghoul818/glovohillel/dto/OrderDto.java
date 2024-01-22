package org.raghoul818.glovohillel.dto;

import lombok.*;

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
