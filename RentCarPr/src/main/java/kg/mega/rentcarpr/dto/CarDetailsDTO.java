package kg.mega.rentcarpr.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class CarDetailsDTO {
    private Long id;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate reservedDates ;
    private CarDTO car;
    private  OrderDTO order;
}
