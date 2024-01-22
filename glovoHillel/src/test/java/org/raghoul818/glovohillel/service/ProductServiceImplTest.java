package org.raghoul818.glovohillel.service;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.raghoul818.glovohillel.dto.ProductDto;
import org.raghoul818.glovohillel.mapper.ProductMapper;
import org.raghoul818.glovohillel.model.Product;
import org.raghoul818.glovohillel.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

    private final Integer ID = 1;

    @InjectMocks
    private ProductServiceImpl testInstance;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @Mock
    private ProductDto productDto;

    @Mock
    private Product product;

    @Test
    public void shouldReturnProductDtoById() {

        when(productRepository.findById(anyInt())).thenReturn(Optional.of(product));

        testInstance.getById(ID);

        verify(productRepository).findById(ID);
    }

    @Test
    public void shouldNotReturnProductDtoById() {

        when(productRepository.findById(anyInt())).thenThrow(RuntimeException.class);

        testInstance.getById(ID);

        assertThrowsExactly(RuntimeException.class, any());
    }

    @Test
    public void shouldReturnListOfProducts() {

        List<Product> list = new ArrayList<>();

        when(productRepository.findAll()).thenReturn(list);

        testInstance.getAll();

        verify(productRepository).findAll();
    }

    @Test
    public void shouldNotReturnListOfProducts() {

        when(productRepository.findAll()).thenThrow(RuntimeException.class);

        testInstance.getAll();

        assertThrowsExactly(RuntimeException.class, any());
    }

    @Test
    public void shouldAddProduct() {

        when(productMapper.productDtoToProduct(productDto)).thenReturn(product);

        Product result = productMapper.productDtoToProduct(productDto);

        testInstance.add(productDto);

        verify(productRepository).save(result);
    }

    @Test
    public void shouldNotAddProduct() {
        when(productMapper.productDtoToProduct(productDto)).thenThrow(RuntimeException.class);

        testInstance.add(productDto);

        assertThrowsExactly(RuntimeException.class, any());
    }

    @Test
    public void shouldUpdateProduct() {

        when(productMapper.productDtoToProduct(productDto)).thenReturn(product);

        Product result = productMapper.productDtoToProduct(productDto);

        testInstance.update(productDto);

        verify(productRepository).save(result);
    }

    @Test
    public void shouldNotUpdateProduct() {
        when(productMapper.productDtoToProduct(productDto)).thenThrow(RuntimeException.class);

        testInstance.update(productDto);

        assertThrowsExactly(RuntimeException.class, any());
    }

    @Test
    public void shouldDeleteProductById() {

        when(productRepository.findById(anyInt())).thenReturn(Optional.of(product));
        when(productMapper.productToProductDto(product)).thenReturn(productDto);

        testInstance.deleteById(ID);

        verify(productRepository).deleteById(ID);
    }
}