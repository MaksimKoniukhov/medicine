package com.medicine.medicine.service;

import com.medicine.medicine.converter.impl.DrugstoreConverter;
import com.medicine.medicine.dto.DrugstoreDto;
import com.medicine.medicine.entity.MedicineEntity;
import com.medicine.medicine.repository.DrugstoreRepository;
import com.medicine.medicine.repository.MedicineRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DrugstoreService {
    private final DrugstoreRepository drugstoreRepository;
    private final DrugstoreConverter drugstoreConverter;
    private final MedicineRepository medicineRepository;

    public DrugstoreService(final DrugstoreRepository drugstoreRepository, final DrugstoreConverter drugstoreConverter, final MedicineRepository medicineRepository) {
        this.drugstoreRepository = drugstoreRepository;
        this.drugstoreConverter = drugstoreConverter;
        this.medicineRepository = medicineRepository;
    }

    public List<DrugstoreDto> searchDrugstoreByMedicine(final String medicineName) {
        final MedicineEntity medicineEntity = medicineRepository.findByMedicine(medicineName);
        return drugstoreRepository.findAllByMedicineEntitySetIsContaining(medicineEntity)
                .stream()
                .map(drugstoreConverter::convertToDto)
                .collect(Collectors.toList());
    }
}
