package org.raghoul818.glovohillel.service;

import org.raghoul818.glovohillel.dto.ProductDto;
import java.util.List;

public interface ProductService {
    ProductDto getById(Integer id);
    List<ProductDto> getAll();
    void add(ProductDto productDto);
    void update(ProductDto productDto);
    void deleteById(Integer id);
}
