package com.medicine.medicine.repository;

import com.medicine.medicine.entity.DrugstoreEntity;
import com.medicine.medicine.entity.MedicineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrugstoreRepository extends JpaRepository<DrugstoreEntity, Long> {

    List<DrugstoreEntity> findAllByMedicineEntitySetIsContaining(final MedicineEntity medicineEntity);
}
