package org.raghoul818.glovohillel.service.product;

import lombok.RequiredArgsConstructor;
import org.raghoul818.glovohillel.dto.product.ProductDto;
import org.raghoul818.glovohillel.mapper.product.ProductMapper;
import org.raghoul818.glovohillel.model.product.Product;
import org.raghoul818.glovohillel.repository.product.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    private Product optionalToEntity(Optional<Product> optionalOfProduct) {
        Product product = new Product();
        if(optionalOfProduct.isPresent()) {
            product = optionalOfProduct.get();
        }
        return product;
    }

    @Override
    public ProductDto getById(Integer id) {
        Optional<Product> optionalOfProduct = productRepository.findById(id);
        Product product = optionalToEntity(optionalOfProduct);
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
    }

    @Override
    public void update(ProductDto productDto) {
        Product product = productMapper.productDtoToProduct(productDto);
        productRepository.save(product);
    }

    @Override
    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }
}
