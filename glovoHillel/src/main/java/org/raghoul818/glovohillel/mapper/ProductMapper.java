package org.raghoul818.glovohillel.mapper;

import org.mapstruct.Mapper;
import org.raghoul818.glovohillel.dto.ProductDto;
import org.raghoul818.glovohillel.model.Product;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface ProductMapper {

    ProductDto productToProductDto(Product entity);

    Product productDtoToProduct(ProductDto dto);

    List<ProductDto> productListToProductDtoList(List<Product> productList);
}
