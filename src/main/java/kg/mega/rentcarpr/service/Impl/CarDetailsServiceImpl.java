package kg.mega.rentcarpr.service.Impl;

import kg.mega.rentcarpr.dto.CarDetailsDTO;
import kg.mega.rentcarpr.mapper.CarDetailsMapper;
import kg.mega.rentcarpr.model.Car;
import kg.mega.rentcarpr.model.CarDetails;
import kg.mega.rentcarpr.model.Order;
import kg.mega.rentcarpr.repository.CarDetailsRepo;
import kg.mega.rentcarpr.repository.CarRepo;
import kg.mega.rentcarpr.repository.OrderRepo;
import kg.mega.rentcarpr.service.CarDetailsService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
@RequiredArgsConstructor
@Getter
@Setter
public class CarDetailsServiceImpl implements CarDetailsService {
  private  final CarDetailsRepo carDetailsRepo;
  private final OrderRepo orderRepo;
  private final CarRepo carRepo;
    @Override
    public CarDetailsDTO saveCarDetails(CarDetailsDTO carDetailsDTO) {
        // Получаем идентификаторы машины и заказа
        Long carId = carDetailsDTO.getCar().getId();
        Long orderId = carDetailsDTO.getOrder().getId();

        // Проверяем, существуют ли машина и заказ с такими идентификаторами
        Car car = carRepo.findById(carId)
                .orElseThrow(() -> new RuntimeException("Машина с указанным идентификатором не найдена"));
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Заказ с указанным идентификатором не найден"));

        // Создаем объект CarDetails и устанавливаем значения машины, заказа и зарезервированных дат
        CarDetails carDetails = new CarDetails();
        carDetails.setCar(car);
        carDetails.setOrder(order);
        carDetails.setReservedDates(carDetailsDTO.getReservedDates());

        // Сохраняем объект CarDetails
        carDetailsRepo.save(carDetails);


        return CarDetailsMapper.INSTANCE.toDto(carDetails);
    }

    @Override
    public List<CarDetailsDTO> getAllOrders() {
        return null;
    }

    @Override
    public List<CarDetails> getCarDetailsByReservedDates(LocalDate reservedDates) {
        return null;
    }
}
