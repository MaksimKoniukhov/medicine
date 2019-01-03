package com.medicine.medicine.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "orders")
@EqualsAndHashCode
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "orders")
    private String order;

    @NotNull
    @Column(name = "date")
    private Date creationDate = new Date();

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "order_medicine",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "medicine_id")
    )
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Set<MedicineEntity> medicineEntitySet = new HashSet<>();

    @ManyToOne
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

}
