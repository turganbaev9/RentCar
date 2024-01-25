package kg.mega.rentcarpr.service;

import kg.mega.rentcarpr.dto.DiscountDTO;
import kg.mega.rentcarpr.model.Car;
import kg.mega.rentcarpr.model.Discount;

import java.util.List;

public interface DiscountService {
    DiscountDTO save(DiscountDTO discount);
    DiscountDTO update(DiscountDTO discountDTO);
    DiscountDTO findById(Long id);
    List<DiscountDTO> findAllDiscount();
    void deleteDiscount(Long id);
    Discount findActive(Car car, Integer daysCount);

}
