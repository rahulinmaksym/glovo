package org.raghoul818.glovohillel.service;

import lombok.RequiredArgsConstructor;
import org.raghoul818.glovohillel.dto.OrderDto;
import org.raghoul818.glovohillel.dto.ProductDto;
import org.raghoul818.glovohillel.mapper.OrderMapper;
import org.raghoul818.glovohillel.model.Order;
import org.raghoul818.glovohillel.repository.OrderRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ProductService productService;

    public Order optionalToEntity(Optional<Order> optionalOfOrder) {
        Order order = new Order();
        if(optionalOfOrder.isPresent()) {
            order = optionalOfOrder.get();
        }
        return order;
    }

    @Override
    public OrderDto getById(Integer id) {
        Optional<Order> optionalOfOrder = orderRepository.findById(id);
        Order order = optionalToEntity(optionalOfOrder);
        return orderMapper.orderToOrderDto(order);
    }

    @Override
    public List<OrderDto> getAll() {
        List<Order> orderList = orderRepository.findAll();
        return orderMapper.orderListToOrderDtoList(orderList);
    }

    @Override
    public void add(OrderDto orderDto) {
        Order order = orderMapper.orderDtoToOrder(orderDto);
        orderRepository.save(order);
    }

    @Override
    public void update(OrderDto orderDto) {
        Order order = orderMapper.orderDtoToOrder(orderDto);
        orderRepository.save(order);
    }

    @Override
    public void deleteById(Integer id) {
        Optional<Order> optionalOfOrder = orderRepository.findById(id);
        Order order = optionalToEntity(optionalOfOrder);
        OrderDto orderDto = orderMapper.orderToOrderDto(order);
        List<ProductDto> productDtoList = orderDto.getProducts();
        for (ProductDto productDto : productDtoList) {
            productService.deleteById(productDto.getId());
        }
        orderRepository.deleteById(id);
    }

    @Override
    public void refreshCostById(Integer id) {
        OrderDto orderDto = getById(id);
        Double cost = 0.0;
        List<ProductDto> productDtoList = getById(id).getProducts();
        for (ProductDto productDto : productDtoList) {
            cost += productDto.getCost();
        }
        orderDto.setCost(cost);
        update(orderDto);
    }
}
