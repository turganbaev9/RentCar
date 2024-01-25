package kg.mega.rentcarpr.service.Impl;

import kg.mega.rentcarpr.dto.DiscountDTO;
import kg.mega.rentcarpr.mapper.DiscountMapper;
import kg.mega.rentcarpr.model.Car;
import kg.mega.rentcarpr.model.Discount;
import kg.mega.rentcarpr.repository.CarRepo;
import kg.mega.rentcarpr.repository.DiscountRepo;
import kg.mega.rentcarpr.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {
    private final DiscountRepo discountRep;
    private final CarRepo carRep;
    @Override
    public DiscountDTO save(DiscountDTO discountDTO) {
        LocalDate startDate=discountDTO.getStartDate();
        LocalDate endDate=discountDTO.getEndDate();
        Integer countDayBetween= Math.toIntExact(ChronoUnit.DAYS.between(startDate, endDate));
        discountDTO.setCountDay(countDayBetween);
        Discount discount = DiscountMapper.INSTANCE.toEntity(discountDTO);
        discount.setCar(carRep.findById(discount.getCar().getId()).get());
        Discount saveDiscount = discountRep.save(discount);
        return DiscountMapper.INSTANCE.toDto(saveDiscount);
    }

    @Override
    public DiscountDTO update(DiscountDTO discountDTO) {
        Discount discount = DiscountMapper.INSTANCE.toEntity(discountDTO);
        Discount updateDiscount = discountRep.findById(discount.getId()).get();
        updateDiscount.setStartDate(discount.getStartDate());
        updateDiscount.setEndDate(discount.getEndDate());
        updateDiscount.setDiscount(discount.getDiscount());
        LocalDate startDate=discountDTO.getStartDate();
        LocalDate endDate=discountDTO.getEndDate();
        Integer countDayBetween= Math.toIntExact(ChronoUnit.DAYS.between(startDate, endDate));
        updateDiscount.setCountDay(countDayBetween);
        return DiscountMapper.INSTANCE.toDto(updateDiscount);
    }

    @Override
    public DiscountDTO findById(Long id) {
        return DiscountMapper.INSTANCE.toDto(discountRep.findById(id).get());
    }

    @Override
    public List<DiscountDTO> findAllDiscount() {
        List<Discount> discounts = discountRep.findAll();
        if (discounts == null) {
            return null;
        }
        return DiscountMapper.INSTANCE.toDtoList(discounts);
    }

    @Override
    public void deleteDiscount(Long id) {
        discountRep.deleteById(id);
    }

    @Override
    public Discount findActive(Car car, Integer daysCount) {
        return discountRep.findActiveByCarAndCountDay(car.getId(),daysCount).orElse(new Discount());
    }
}
