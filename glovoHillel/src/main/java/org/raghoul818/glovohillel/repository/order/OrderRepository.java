package org.raghoul818.glovohillel.repository.order;

import org.raghoul818.glovohillel.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}


