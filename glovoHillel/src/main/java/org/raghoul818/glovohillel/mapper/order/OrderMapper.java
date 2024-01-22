package org.raghoul818.glovohillel.mapper.order;

import org.mapstruct.Mapper;
import org.raghoul818.glovohillel.dto.order.OrderDto;
import org.raghoul818.glovohillel.mapper.product.ProductMapper;
import org.raghoul818.glovohillel.model.order.Order;
import java.util.List;

@Mapper(
        uses = {ProductMapper.class},
        componentModel = "spring"
)
public interface OrderMapper {

    OrderDto orderToOrderDto(Order entity);

    Order orderDtoToOrder(OrderDto dto);

    List<OrderDto> orderListToOrderDtoList(List<Order> orderList);
}
