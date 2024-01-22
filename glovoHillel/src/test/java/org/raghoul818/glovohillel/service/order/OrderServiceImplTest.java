package org.raghoul818.glovohillel.service.order;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.raghoul818.glovohillel.dto.order.OrderDto;
import org.raghoul818.glovohillel.mapper.order.OrderMapper;
import org.raghoul818.glovohillel.model.order.Order;
import org.raghoul818.glovohillel.repository.order.OrderRepository;
import org.raghoul818.glovohillel.service.product.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

    private final Integer ID = 1;

    @InjectMocks
    private OrderServiceImpl testInstance;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private ProductService productService;

    @Mock
    private OrderDto orderDto;

    @Mock
    private Order order;

    @Test
    public void shouldReturnOrderDtoById() {

        when(orderRepository.findById(anyInt())).thenReturn(Optional.of(order));

        testInstance.getById(ID);

        verify(orderRepository).findById(ID);
    }

    @Test
    public void shouldNotReturnOrderDtoById() {

        when(orderRepository.findById(anyInt())).thenThrow(RuntimeException.class);

        testInstance.getById(ID);

        assertThrowsExactly(RuntimeException.class, any());
    }

    @Test
    public void shouldReturnListOfOrders() {

        List<Order> list = new ArrayList<>();

        when(orderRepository.findAll()).thenReturn(list);

        testInstance.getAll();

        verify(orderRepository).findAll();
    }

    @Test
    public void shouldNotReturnListOfOrders() {

        when(orderRepository.findAll()).thenThrow(RuntimeException.class);

        testInstance.getAll();

        assertThrowsExactly(RuntimeException.class, any());
    }

    @Test
    public void shouldAddOrder() {

        when(orderMapper.orderDtoToOrder(orderDto)).thenReturn(order);

        Order result = orderMapper.orderDtoToOrder(orderDto);

        testInstance.add(orderDto);

        verify(orderRepository).save(result);
    }

    @Test
    public void shouldNotAddOrder() {
        when(orderMapper.orderDtoToOrder(orderDto)).thenThrow(RuntimeException.class);

        testInstance.add(orderDto);

        assertThrowsExactly(RuntimeException.class, any());
    }

    @Test
    public void shouldUpdateOrder() {

        when(orderMapper.orderDtoToOrder(orderDto)).thenReturn(order);

        Order result = orderMapper.orderDtoToOrder(orderDto);

        testInstance.update(orderDto);

        verify(orderRepository).save(result);
    }

    @Test
    public void shouldNotUpdateOrder() {

        when(orderMapper.orderDtoToOrder(orderDto)).thenThrow(RuntimeException.class);

        testInstance.update(orderDto);

        assertThrowsExactly(RuntimeException.class, any());
    }

    @Test
    public void shouldDeleteOrderById() {

        when(orderRepository.findById(anyInt())).thenReturn(Optional.of(order));
        when(orderMapper.orderToOrderDto(order)).thenReturn(orderDto);

        testInstance.deleteById(ID);

        verify(orderRepository).deleteById(ID);
    }

    @Test
    public void shouldNotDeleteOrderById() {

        when(orderRepository.findById(anyInt())).thenThrow(RuntimeException.class);

        testInstance.deleteById(ID);

        assertThrowsExactly(RuntimeException.class, any());
    }

    @Test
    public void shouldRefreshCostById() {

        when(orderRepository.findById(anyInt())).thenReturn(Optional.of(order));
        when(orderMapper.orderDtoToOrder(orderDto)).thenReturn(order);
        when(orderMapper.orderToOrderDto(order)).thenReturn(orderDto);

        Order result = orderMapper.orderDtoToOrder(orderDto);

        testInstance.refreshCostById(ID);

        verify(orderRepository, times(2)).findById(ID);
        assert result != null;
        verify(orderRepository).save(result);
    }

    @Test
    public void shouldNotRefreshCostById() {
        when(orderRepository.findById(anyInt())).thenThrow(RuntimeException.class);

        testInstance.refreshCostById(ID);

        assertThrowsExactly(RuntimeException.class, any());
    }
}