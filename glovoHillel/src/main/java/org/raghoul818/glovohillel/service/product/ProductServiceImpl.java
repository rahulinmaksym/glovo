package org.raghoul818.glovohillel.service.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.raghoul818.glovohillel.dto.product.ProductDto;
import org.raghoul818.glovohillel.mapper.product.ProductMapper;
import org.raghoul818.glovohillel.model.product.Product;
import org.raghoul818.glovohillel.repository.product.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductDto getById(Integer id) {

        Optional<Product> optionalOfProduct = productRepository.findById(id);

        Product product = optionalOfProduct.orElse(null);
        if(product == null) log.error("Unable to get a product for there is no such");

        return productMapper.productToProductDto(product);
    }

    @Override
    public List<ProductDto> getAll() {

        List<Product> productList = productRepository.findAll();

        return productMapper.productListToProductDtoList(productList);
    }

    @Override
    public void add(ProductDto productDto) {

        Product product = productMapper.productDtoToProduct(productDto);

        productRepository.save(product);
        log.debug("Product has been added: " + product);
    }

    @Override
    public void update(ProductDto productDto) {

        Product product = productMapper.productDtoToProduct(productDto);

        productRepository.save(product);
        log.debug("Product has been updated: " + product);
    }

    @Override
    public void deleteById(Integer id) {

        log.trace("Going to delete a product with id " + id);
        productRepository.deleteById(id);
    }
}
