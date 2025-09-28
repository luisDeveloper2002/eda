package co.edu.uptc.swii.edamicrokafka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.uptc.swii.edamicrokafka.model.Login;

public interface LoginRepository extends JpaRepository<Login, Integer> {
    Login findByCustomerId(String customerId);
}
