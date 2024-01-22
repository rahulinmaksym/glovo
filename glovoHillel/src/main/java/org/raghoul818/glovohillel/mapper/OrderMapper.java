package org.raghoul818.glovohillel.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.raghoul818.glovohillel.dto.OrderDto;
import org.raghoul818.glovohillel.model.Order;
import java.util.List;

@Mapper(
        uses = {ProductMapper.class},
        componentModel = "spring"
)
public interface OrderMapper {

    @Mapping(
            target = "date",
            source = "entity.date",
            dateFormat = "dd-MM-yyyy"
    )
    OrderDto orderToOrderDto(Order entity);

    @Mapping(
            target = "date",
            source = "dto.date",
            dateFormat = "dd-MM-yyyy"
    )
    Order orderDtoToOrder(OrderDto dto);

    List<OrderDto> orderListToOrderDtoList(List<Order> orderList);
}
