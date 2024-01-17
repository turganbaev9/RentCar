package kg.mega.rentcarpr.service;

import kg.mega.rentcarpr.dto.CarDetailsDTO;
import kg.mega.rentcarpr.model.CarDetails;

import java.time.LocalDate;
import java.util.List;

public interface CarDetailsService {
    CarDetailsDTO saveCarDetails(CarDetailsDTO carDetailsDTO);
    List<CarDetailsDTO> getAllOrders();
    List<CarDetails> getCarDetailsByReservedDates(LocalDate reservedDates);


}
