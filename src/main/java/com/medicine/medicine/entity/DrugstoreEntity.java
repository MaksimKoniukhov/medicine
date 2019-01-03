package com.medicine.medicine.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "drugstore")
@Data
public class DrugstoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "country")
    private String country;

    @NotNull
    @Column(name = "city")
    private String city;

    @NotNull
    @Column(name = "street")
    private String street;

    @NotNull
    @Column(name = "home_number")
    private int homeNumber;

    @NotNull
    @Column(name = "opening_time")
    private LocalTime openingTime;

    @NotNull
    @Column(name = "closing_time")
    private LocalTime closingTime;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "drugstore_medicine",
            joinColumns = @JoinColumn(name = "drugstore_id"),
            inverseJoinColumns = @JoinColumn(name = "medicine_id")
    )
    @EqualsAndHashCode.Exclude
    private Set<MedicineEntity> medicineEntitySet;
}
