package kg.mega.rentcarpr.repository;

import kg.mega.rentcarpr.model.Car;
import kg.mega.rentcarpr.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PriceRepo extends JpaRepository<Price,Long> {
    Optional<Price> findByCar(Car car);
}
