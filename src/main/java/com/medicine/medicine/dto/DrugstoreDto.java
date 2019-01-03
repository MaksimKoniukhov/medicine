package com.medicine.medicine.dto;

import lombok.Data;

import java.time.LocalTime;
import java.util.Set;

@Data
public class DrugstoreDto {

    private long id;

    private String name;

    private String country;

    private String city;

    private String street;

    private int homeNumber;

    private LocalTime openingTime;

    private LocalTime closingTime;

    private Set<MedicineDto> medicineDtoSet;
}
