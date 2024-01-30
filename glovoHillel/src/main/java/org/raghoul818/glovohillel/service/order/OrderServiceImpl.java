package org.raghoul818.glovohillel.service.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.raghoul818.glovohillel.dto.order.OrderDto;
import org.raghoul818.glovohillel.dto.product.ProductDto;
import org.raghoul818.glovohillel.mapper.order.OrderMapper;
import org.raghoul818.glovohillel.model.order.Order;
import org.raghoul818.glovohillel.repository.order.OrderRepository;
import org.raghoul818.glovohillel.service.product.ProductService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ProductService productService;

    @Override
    public OrderDto getById(Integer id) {

        Optional<Order> optionalOfOrder = orderRepository.findById(id);

        Order order = optionalOfOrder.orElse(null);
        if(order == null) log.error("Unable to get an order for there is no such");

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
        log.debug("Order has been added: " + order);
    }

    @Override
    public void update(OrderDto orderDto) {

        Order order = orderMapper.orderDtoToOrder(orderDto);

        orderRepository.save(order);
        log.debug("Order has been updated: " + order);
    }

    @Override
    public void deleteById(Integer id) {

        Optional<Order> optionalOfOrder = orderRepository.findById(id);

        Order order = optionalOfOrder.orElse(null);
        if(order == null) log.error("Unable to get an order for there is no such");

        OrderDto orderDto = orderMapper.orderToOrderDto(order);

        List<ProductDto> productDtoList = orderDto.getProducts();
        for (ProductDto productDto : productDtoList) {
            productService.deleteById(productDto.getId());
        }

        log.trace("Going to delete an order with id " + id);
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
