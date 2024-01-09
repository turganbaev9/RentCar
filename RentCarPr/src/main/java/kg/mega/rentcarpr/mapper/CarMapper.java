package kg.mega.rentcarpr.mapper;

import kg.mega.rentcarpr.dto.CarDTO;
import kg.mega.rentcarpr.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CarMapper {

    CarMapper INSTANCE= Mappers.getMapper(CarMapper.class);
    Car toEntity(CarDTO car);
    CarDTO toDto(Car car);
    List<Car> toEntityList(List<CarDTO>carDTOList);
    List<CarDTO>toDTOList(List<Car >carList);
}
