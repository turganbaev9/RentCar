package kg.mega.rentcarpr.repository;

import kg.mega.rentcarpr.model.CarDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CarDetailsRepo extends JpaRepository<CarDetails,Long> {

    List<CarDetails> findByReservedDates(LocalDate reservedDates);
}