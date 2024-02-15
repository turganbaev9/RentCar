package kg.mega.rentcarpr.mapper;

import kg.mega.rentcarpr.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE= Mappers.getMapper(AddressMapper.class);
    Address toEntity(AddressDTO addressDTO);
    AddressDTO toDto(Address address);
    List<Address> toEntityList(List<AddressDTO>addressDTOList);
    List<AddressDTO>toDTOList(List<Address > addressList);
}
}
