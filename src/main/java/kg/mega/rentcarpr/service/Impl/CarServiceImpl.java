package kg.mega.rentcarpr.service.Impl;

import kg.mega.rentcarpr.dto.CarDTO;
import kg.mega.rentcarpr.dto.OrderDTO;
import kg.mega.rentcarpr.mapper.CarMapper;
import kg.mega.rentcarpr.mapper.OrderMapper;
import kg.mega.rentcarpr.model.Car;
import kg.mega.rentcarpr.repository.CarRepo;
import kg.mega.rentcarpr.repository.OrderRepo;
import kg.mega.rentcarpr.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private  final CarRepo carRepo;
    private  final OrderRepo orderRepo;
    @Override
    public CarDTO save(CarDTO carDTO, MultipartFile file) {
        Car car= CarMapper.INSTANCE.toEntity(carDTO);
        car.setPhoto(!fileDownload(file).equals("")?fileDownload(file):"file not saved");
    Car saved=carRepo.save(car);
    return CarMapper.INSTANCE.toDto(saved);}
    private String fileDownload(MultipartFile file){
        try {
            File path=new File("D:\\image\\carsImages\\"+file.getOriginalFilename());
            path.createNewFile();
            FileOutputStream output=new FileOutputStream(path);
            output.write(file.getBytes());
            output.close();


            return path.getAbsolutePath();
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public CarDTO update(CarDTO carDTO) {
        Car car = CarMapper.INSTANCE.toEntity(carDTO);
        Car update = carRepo.findById(car.getId()).get();
        update.setModel(car.getModel());
        update.setColor(car.getColor());
        update.setCategory(car.getCategory());
        update.setPhoto(car.getPhoto());
        update.setEngineType(car.getEngineType());
        update.setEngineVolume(car.getEngineVolume());
        update.setMnfYear(car.getMnfYear());
        update.setGasPer100(car.getGasPer100());
        return CarMapper.INSTANCE.toDto(update);
    }

    @Override
    public CarDTO findById(Long id) {

            Car find = carRepo.findById(id).get();

            return CarMapper.INSTANCE.toDto(find);

    }

    @Override
    public List<CarDTO> findAllCar() {
        List<Car> cars = carRepo.findAll();
        if (cars == null) {
            return null;
        }
        return CarMapper.INSTANCE.toDTOList(cars);
    }

    @Override
    public void deleteCar(Long id) {
        carRepo.deleteById(id);
    }


    @Override
    public boolean isAvailable(CarDTO car, LocalDate startDate, LocalDate endDate) {
        LocalDate now = LocalDate.now();
        List<OrderDTO> allOrders = OrderMapper.INSTANCE.toDTOList(orderRepo.findAll());
        for (OrderDTO order : allOrders) {
            if (order.getCar().getId().equals(car.getId())) {
                if (order.getDateTimeFrom().isBefore(now) && order.getDateTimeTo().isAfter(now)) {
                    if ((startDate.isAfter(order.getDateTimeFrom()) && startDate.isBefore(order.getDateTimeTo()))
                            || (endDate.isAfter(order.getDateTimeFrom()) && endDate.isBefore(order.getDateTimeTo()))
                            || (startDate.isBefore(order.getDateTimeFrom()) && endDate.isAfter(order.getDateTimeTo()))) {
                        System.out.println("Машина занята в указанный период");
                        return false;
                    }
                }
            }
        }
        return true;
    }
    }

