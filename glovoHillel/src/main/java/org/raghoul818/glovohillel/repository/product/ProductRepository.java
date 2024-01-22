package org.raghoul818.glovohillel.repository.product;

import org.raghoul818.glovohillel.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
