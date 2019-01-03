package com.medicine.medicine.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "medicine")
@EqualsAndHashCode
public class MedicineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "medicine")
    private String medicine;

    @NotNull
    @Column(name = "price")
    private int price;

    @ManyToMany(mappedBy = "medicineEntitySet", cascade = CascadeType.ALL)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<OrderEntity> orderEntitySet = new HashSet<>();

    @ManyToMany(mappedBy = "medicineEntitySet", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<DrugstoreEntity> drugstoreEntitySet;
}
