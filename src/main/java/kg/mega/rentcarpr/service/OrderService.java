package kg.mega.rentcarpr.service;

import kg.mega.rentcarpr.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO save(OrderDTO orderDTO);
    OrderDTO update(OrderDTO orderDTO);
    List<OrderDTO> findAllOrder();
    void deleteOrder(Long id);
    OrderDTO findById(Long id);
}
