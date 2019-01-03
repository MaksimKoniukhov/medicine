package com.medicine.medicine.dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class DrugstoreDtoWithoutMedicine {

    private long id;

    private String name;

    private String country;

    private String city;

    private String street;

    private int homeNumber;

    private LocalTime openingTime;

    private LocalTime closingTime;
}
