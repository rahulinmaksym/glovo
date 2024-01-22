package org.raghoul818.glovohillel.controller;

import lombok.RequiredArgsConstructor;
import org.raghoul818.glovohillel.dto.OrderDto;
import org.raghoul818.glovohillel.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getById(@PathVariable("orderId") Integer orderId) {

        OrderDto orderDto = orderService.getById(orderId);

        if(orderDto != null) {
            return ResponseEntity.ok(orderDto);
        }
        return (ResponseEntity<OrderDto>) ResponseEntity.notFound();
    }

    @CrossOrigin("*")
    @GetMapping()
    public ResponseEntity<List<OrderDto>> getAll() {
        List<OrderDto> orderDtoList = orderService.getAll();

        if(!CollectionUtils.isEmpty(orderDtoList)) {
            return ResponseEntity.ok(orderDtoList);
        }
        return (ResponseEntity<List<OrderDto>>) ResponseEntity.notFound();
    }

    @CrossOrigin("*")
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody OrderDto orderDto) {
        orderService.add(orderDto);
    }

    @CrossOrigin("*")
    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody OrderDto orderDto) {
        orderService.update(orderDto);
    }

    @PutMapping("/{orderId}/refreshCost")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void refreshCostById(@PathVariable("orderId") Integer orderId) {
        orderService.refreshCostById(orderId);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("orderId") Integer orderId) {
        orderService.deleteById(orderId);
    }
}
