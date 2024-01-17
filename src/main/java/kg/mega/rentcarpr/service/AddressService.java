package kg.mega.rentcarpr.service;

import kg.mega.rentcarpr.dto.AddressDTO;

import java.util.List;

public interface AddressService {
    AddressDTO saveAddress(AddressDTO addressDTO);
    AddressDTO update(AddressDTO addressDTO);
    AddressDTO findById(Long id);
    List<AddressDTO> findAllAddress();
    void deleteAddress(Long id);
}
