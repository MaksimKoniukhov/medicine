package com.medicine.medicine.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
public class OrderDto {
    private long id;
    private String order;
    private Date creationDate;
    private Set<MedicineDto> medicineDtoSet = new HashSet<>();
//    private UserDto user;
}
