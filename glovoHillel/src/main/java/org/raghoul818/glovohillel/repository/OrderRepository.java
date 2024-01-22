package org.raghoul818.glovohillel.repository;

import org.raghoul818.glovohillel.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}


