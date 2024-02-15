package kg.mega.rentcarpr.repository;

import kg.mega.rentcarpr.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepo  extends JpaRepository<Car,Long> {
}
