package kg.mega.rentcarpr.mapper;

import kg.mega.rentcarpr.model.CarDetails;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface CarDetailsMapper {
    CarDetailsMapper INSTANCE = Mappers.getMapper(CarDetailsMapper.class);

    CarDetails toEntity(CarDetailsDTO carDetailsDTO);

    CarDetailsDTO toDto(CarDetails carDetails);

    List<CarDetails> toEntityList(List<CarDetailsDTO> carDetailsDTOS);

    List<CarDetailsDTO> toDTOList(List<CarDetails> carDetails);
}

