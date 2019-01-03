package com.medicine.medicine.converter.impl;

import com.medicine.medicine.converter.DtoDboConverter;
import com.medicine.medicine.dto.DrugstoreDto;
import com.medicine.medicine.dto.DrugstoreDtoWithoutMedicine;
import com.medicine.medicine.entity.DrugstoreEntity;
import com.medicine.medicine.repository.DrugstoreRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrugstoreConverter implements DtoDboConverter<DrugstoreDto, DrugstoreEntity> {

    @Autowired
    private DrugstoreRepository drugstoreRepository;
    @Autowired
    private MedicineConverter medicineConverter;

    @Override
    public DrugstoreDto convertToDto(final DrugstoreEntity entity) {
        final DrugstoreDto drugstoreDto = new DrugstoreDto();
        BeanUtils.copyProperties(entity, drugstoreDto);
        drugstoreDto.setMedicineDtoSet(medicineConverter.convertSetToDto(entity.getMedicineEntitySet()));
        return drugstoreDto;
    }

    @Override
    public DrugstoreEntity convertToDbo(final DrugstoreDto dto) {
        final DrugstoreEntity drugstoreEntity = new DrugstoreEntity();
        BeanUtils.copyProperties(dto, drugstoreEntity);
        drugstoreEntity.setMedicineEntitySet(medicineConverter.convertSetToDbo(dto.getMedicineDtoSet()));
        return drugstoreEntity;
    }

    public DrugstoreDtoWithoutMedicine convertToDtoWithoutMedicine(final DrugstoreEntity entity) {
        final DrugstoreDtoWithoutMedicine dtoWithoutMedicine = new DrugstoreDtoWithoutMedicine();
        BeanUtils.copyProperties(entity, dtoWithoutMedicine);
        return dtoWithoutMedicine;
    }

    public DrugstoreEntity convertToDboFromDtoWithoutMedicine(final DrugstoreDtoWithoutMedicine dtoWithoutMedicine) {
        final DrugstoreEntity drugstoreEntity = new DrugstoreEntity();
        BeanUtils.copyProperties(dtoWithoutMedicine, drugstoreEntity);
        drugstoreEntity.setMedicineEntitySet(drugstoreRepository.findById(dtoWithoutMedicine.getId())
                .orElse(new DrugstoreEntity()).getMedicineEntitySet());
        return drugstoreEntity;
    }
}
