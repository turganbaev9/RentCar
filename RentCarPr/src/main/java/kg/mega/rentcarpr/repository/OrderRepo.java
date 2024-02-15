package kg.mega.rentcarpr.repository;

import kg.mega.rentcarpr.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface OrderRepo extends JpaRepository<Order,Long> {
    @Query("SELECT DISTINCT o.dateTimeTo FROM Order o")
    LocalDate findReservedDates();


}
