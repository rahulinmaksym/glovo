package org.raghoul818.glovohillel.service.order;

import org.raghoul818.glovohillel.dto.order.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto getById(Integer id);
    List<OrderDto> getAll();
    void add(OrderDto orderDto);
    void update(OrderDto orderDto);
    void deleteById(Integer id);
    void refreshCostById(Integer id);
}
