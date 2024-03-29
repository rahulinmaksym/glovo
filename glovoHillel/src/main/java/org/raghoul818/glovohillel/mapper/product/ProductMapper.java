package org.raghoul818.glovohillel.mapper.product;

import org.mapstruct.Mapper;
import org.raghoul818.glovohillel.dto.product.ProductDto;
import org.raghoul818.glovohillel.model.product.Product;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface ProductMapper {

    ProductDto productToProductDto(Product entity);

    Product productDtoToProduct(ProductDto dto);

    List<ProductDto> productListToProductDtoList(List<Product> productList);
}
