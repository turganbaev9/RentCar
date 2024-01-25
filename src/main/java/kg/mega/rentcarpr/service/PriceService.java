package kg.mega.rentcarpr.service;

import kg.mega.rentcarpr.dto.PriceDTO;

import java.util.List;

public interface PriceService {
    PriceDTO save(PriceDTO priceDTO);
    PriceDTO update(PriceDTO priceDTO);
    PriceDTO finById(Long id);
    List<PriceDTO> findAll();
    void deletePrice(Long id);
}
