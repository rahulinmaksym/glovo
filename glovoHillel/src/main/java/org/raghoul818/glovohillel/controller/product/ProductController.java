package org.raghoul818.glovohillel.controller.product;

import lombok.RequiredArgsConstructor;
import org.raghoul818.glovohillel.dto.product.ProductDto;
import org.raghoul818.glovohillel.service.product.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getById(@PathVariable("productId") Integer productId) {

        ProductDto productDto = productService.getById(productId);

        if(productDto != null) {
            return ResponseEntity.ok(productDto);
        }
        return (ResponseEntity<ProductDto>) ResponseEntity.notFound();
    }

    @CrossOrigin("*")
    @GetMapping()
    public ResponseEntity<List<ProductDto>> getAll() {
        List<ProductDto> productDtoList = productService.getAll();

        if(!CollectionUtils.isEmpty(productDtoList)) {
            return ResponseEntity.ok(productDtoList);
        }
        return (ResponseEntity<List<ProductDto>>) ResponseEntity.notFound();
    }

    @CrossOrigin("*")
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody ProductDto productDto) {
        productService.add(productDto);
    }

    @CrossOrigin("*")
    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody ProductDto productDto) {
        productService.update(productDto);
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("productId") Integer productId) {
        productService.deleteById(productId);
    }
}
