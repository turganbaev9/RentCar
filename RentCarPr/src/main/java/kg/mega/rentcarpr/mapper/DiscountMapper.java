package kg.mega.rentcarpr.mapper;

import kg.mega.rentcarpr.dto.DiscountDTO;
import kg.mega.rentcarpr.model.Discount;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DiscountMapper {
    DiscountMapper INSTANCE= Mappers.getMapper(DiscountMapper.class);
    Discount toEntity(DiscountDTO discountDTO);
    DiscountDTO toDto(Discount discount);
    List<Discount> toEntityList(List<DiscountDTO>discountDTOList);

    List<DiscountDTO> toDtoList(List<Discount>discountList);
}
