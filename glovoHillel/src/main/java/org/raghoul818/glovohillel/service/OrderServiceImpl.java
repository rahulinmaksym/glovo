package org.raghoul818.glovohillel.service;

import lombok.RequiredArgsConstructor;
import org.raghoul818.glovohillel.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
}
