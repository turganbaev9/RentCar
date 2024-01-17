package kg.mega.rentcarpr.service.Impl;

import kg.mega.rentcarpr.dto.OrderDTO;
import kg.mega.rentcarpr.mapper.OrderMapper;
import kg.mega.rentcarpr.model.*;
import kg.mega.rentcarpr.repository.*;
import kg.mega.rentcarpr.service.CarService;
import kg.mega.rentcarpr.service.DiscountService;
import kg.mega.rentcarpr.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRep;
    private final DiscountService discountService;
    private final CarRepo carRep;
    private final AddressRepo addressRep;
    private final DiscountRepo discountRep;
    private final PriceRepo priceRep;
    private final CarService carService;
    @Override
    public OrderDTO save(OrderDTO orderDTO) {
        Order order = OrderMapper.INSTANCE.toEntity(orderDTO);

        // Получаем объекты Car, Address и ReturnAddress по id
        Car car = carRep.findById(orderDTO.getCar().getId())
                .orElseThrow(() -> new NoSuchElementException("Car not found"));
        Address address = addressRep.findById(orderDTO.getAddress().getId())
                .orElseThrow(() -> new NoSuchElementException("Address not found"));
        Address returnAddress = addressRep.findById(orderDTO.getReturnAddress().getId())
                .orElseThrow(() -> new NoSuchElementException("Return address not found"));
        order.setCar(car);
        order.setAddress(address);
        order.setReturnAddress(returnAddress);
        LocalDate startDate = orderDTO.getDateTimeFrom();
        LocalDate endDate = orderDTO.getDateTimeTo();
        // Проверяется доступность автомобиля
        if (!carService.isAvailable(orderDTO.getCar(),startDate,endDate)) {

            return null;
        }

        long duration = ChronoUnit.DAYS.between(startDate,endDate);
        // Нахожу цену для автомобиля
        Price price = priceRep.findByCar(car)
                .orElseThrow(() -> new NoSuchElementException("Price not found for car " + car.getId()));
        Double carPrice = price.getPrice();
        Double beforeTotalPrice = carPrice * duration;
        order.setPrice(price);
        order.setPriceBeforeDiscount(beforeTotalPrice);
        // Расчет скидки
        Discount calculatedDiscount = discountService.findActive(car, (int) duration);
        Double totalDiscount = calculatedDiscount.getDiscount();
        order.setDiscount(calculatedDiscount);

        // Расчет цены с учетом скидки
        Double totalPrice = beforeTotalPrice - (beforeTotalPrice * totalDiscount / 100);
        order.setPriceWithDiscount(beforeTotalPrice);
        order.setPriceWithDiscount(totalPrice);

       // sendMail(order.getClientEmail(),"Your invoice saved"+totalPrice.doubleValue(),toString());
        Order savedOrder = orderRep.save(order);
        return OrderMapper.INSTANCE.toDto(savedOrder);
    }


    @Override
    public OrderDTO update(OrderDTO orderDTO) {
        Order order = OrderMapper.INSTANCE.toEntity(orderDTO);
        Order updateOrder = orderRep.findById(order.getId()).get();
        updateOrder.setBabySeat(order.isBabySeat());
        updateOrder.setWithDriver(order.isWithDriver());
        updateOrder.setClientName(order.getClientName());
        updateOrder.setClientEmail(order.getClientEmail());
        updateOrder.setClientPhone(order.getClientPhone());
        updateOrder.setDateTimeFrom(order.getDateTimeFrom());
        updateOrder.setDateTimeTo(order.getDateTimeTo());
        return OrderMapper.INSTANCE.toDto(updateOrder);
    }

    @Override
    public List<OrderDTO> findAllOrder() {
        List<Order>orders=orderRep.findAll();
        return OrderMapper.INSTANCE.toDTOList(orders);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRep.deleteById(id);
    }

    @Override
    public OrderDTO findById(Long id) {
        return OrderMapper.INSTANCE.toDto(orderRep.findById(id).get());
    }
}
