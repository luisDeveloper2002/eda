package co.edu.uptc.swii.edamicrokafka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.uptc.swii.edamicrokafka.model.Order;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerId(String customerId);
}
