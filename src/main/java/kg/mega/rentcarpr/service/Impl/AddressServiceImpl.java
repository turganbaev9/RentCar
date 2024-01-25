package kg.mega.rentcarpr.service.Impl;

import kg.mega.rentcarpr.dto.AddressDTO;
import kg.mega.rentcarpr.mapper.AddressMapper;
import kg.mega.rentcarpr.model.Address;
import kg.mega.rentcarpr.repository.AddressRepo;
import kg.mega.rentcarpr.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private  final AddressRepo addressRepo;
    @Override
    public AddressDTO saveAddress(AddressDTO addressDTO) {
        Address address= AddressMapper.INSTANCE.toEntity(addressDTO);
        addressRepo.save(address);
        return AddressMapper.INSTANCE.toDto(address);
    }

    @Override
    public AddressDTO update(AddressDTO addressDTO) {
        if (addressDTO==null){
            System.err.println("Аддресс имеет нулевое значение");
        }
        Address address=AddressMapper.INSTANCE.toEntity(addressDTO);
        Address updated=addressRepo.findById(address.getId()).orElseThrow(()->new NoSuchElementException("Address with that Id not found"));
        updated.setCity(address.getCity());
        updated.setStreet(address.getStreet());
        updated.setBuildingNum(address.getBuildingNum());
        Address updt=addressRepo.save(updated);
        return AddressMapper.INSTANCE.toDto(updt);
    }

    @Override
    public AddressDTO findById(Long id) {
        return AddressMapper.INSTANCE.toDto(addressRepo.findById(id).orElseThrow(()-> new EntityNotFoundException("Address with this Id not found")));
    }

    @Override
    public List<AddressDTO> findAllAddress() {
     List<Address>addresses=addressRepo.findAll();
     if (addresses==null){
         System.err.println("Address with this Id not found or its hasnt");
     }
        return  AddressMapper.INSTANCE.toDTOList(addresses);
    }

    @Override
    public void deleteAddress(Long id) {
addressRepo.deleteById(id);
    }
}
