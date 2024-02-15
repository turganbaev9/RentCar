package kg.mega.rentcarpr.repository;

import kg.mega.rentcarpr.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepo extends JpaRepository<Address,Long> {
Optional<Address>findById(Long id);
}
