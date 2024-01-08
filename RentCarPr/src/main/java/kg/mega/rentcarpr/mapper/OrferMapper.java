package kg.mega.rentcarpr.mapper;

import kg.mega.rentcarpr.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrferMapper {
    OrderMapper INSTANCE= Mappers.getMapper(OrderMapper.class);
    Order toEntity(OrderDTO orderDTO);
    OrderDTO toDto(Order order);
    List<Order> toEntityList(List<OrderDTO>orderDTOList);
    List<OrderDTO>toDTOList(List<Order >orderList);
}

