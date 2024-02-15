package kg.mega.rentcarpr.mapper;

import kg.mega.rentcarpr.dto.PriceDTO;
import kg.mega.rentcarpr.model.Price;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PriceMapper {
    PriceMapper INSTANCE= Mappers.getMapper(PriceMapper.class);
    Price toEntity(PriceDTO priceDTO);
    PriceDTO toDto(Price price);
    List<Price> toEntityList(List<PriceDTO>priceDTOList);
    List<PriceDTO>toDTOList(List<Price>priceList);

}

