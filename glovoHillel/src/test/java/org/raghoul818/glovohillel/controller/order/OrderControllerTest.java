package org.raghoul818.glovohillel.controller.order;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.raghoul818.glovohillel.dto.order.OrderDto;
import org.raghoul818.glovohillel.model.order.Order;
import org.raghoul818.glovohillel.model.product.Product;
import org.raghoul818.glovohillel.repository.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDate;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("dev")
public class OrderControllerTest {

    @Value("${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void shouldGetOrderById() {
        Order order = Order.builder()
                .date(LocalDate.now())
                .cost(14.88)
                .products(List.of(Product.builder()
                        .cost(13.37)
                        .name("test")
                        .build()))
                .build();
        Order savedOrder = orderRepository.save(order);

        OrderDto result = restTemplate.getForObject(
                "http://localhost:" + port + "/api/v1/orders/"
                + savedOrder.getId(), OrderDto.class
        );

        Assertions.assertEquals(savedOrder.getId(), result.getId());
    }

    @Test
    public void whenCreateOrder_thenStatus201() {
        Order order = Order.builder()
                .date(LocalDate.now())
                .cost(14.88)
                .products(List.of(Product.builder()
                        .cost(13.37)
                        .name("test")
                        .build()))
                .build();
        Order savedOrder = orderRepository.save(order);

        OrderDto result = restTemplate.getForObject(
                "http://localhost:" + port + "/api/v1/orders/"
                        + savedOrder.getId(), OrderDto.class
        );

        ResponseEntity<OrderDto> response = restTemplate.postForEntity(
                "http://localhost:" + port + "/api/v1/orders", result, OrderDto.class
        );

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }
}