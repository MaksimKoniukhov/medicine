package com.medicine.medicine.converter.impl;

import com.medicine.medicine.converter.DtoDboConverter;
import com.medicine.medicine.dto.DrugstoreDtoWithoutMedicine;
import com.medicine.medicine.dto.MedicineDto;
import com.medicine.medicine.entity.DrugstoreEntity;
import com.medicine.medicine.entity.MedicineEntity;
import com.medicine.medicine.repository.MedicineRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
//@AllArgsConstructor
public class MedicineConverter implements DtoDboConverter<MedicineDto, MedicineEntity> {
    @Autowired
    private MedicineRepository medicineRepository;
    @Autowired
    private DrugstoreConverter drugstoreConverter;

//    private OrderConverter orderConverter;

    @Override
    public MedicineDto convertToDto(MedicineEntity entity) {
        final MedicineDto medicineDto = new MedicineDto();
        BeanUtils.copyProperties(entity, medicineDto);
        Set<DrugstoreEntity> drugstoreEntitySet = entity.getDrugstoreEntitySet();
        if (drugstoreEntitySet != null) {
            drugstoreEntitySet.forEach(drugstoreEntity -> medicineDto.getDrugstoreDtoWithoutMedicineSet()
                    .add(drugstoreConverter.convertToDtoWithoutMedicine(drugstoreEntity)));
        }

//        medicineDto
//                .setOrderDtoSet(orderConverter
//                        .convertSetToDto(entity
//                                .getOrderEntitySet()));
        return medicineDto;
    }

    @Override
    public MedicineEntity convertToDbo(MedicineDto dto) {
        final MedicineEntity medicineEntity = new MedicineEntity();
        BeanUtils.copyProperties(dto, medicineEntity);
        medicineEntity.setOrderEntitySet(medicineRepository.findById(dto.getId())
                .orElse(new MedicineEntity()).getOrderEntitySet());
        Set<DrugstoreDtoWithoutMedicine> dtoWithoutMedicineSet = dto.getDrugstoreDtoWithoutMedicineSet();
        if (dtoWithoutMedicineSet != null) {
            dtoWithoutMedicineSet.forEach(dtoWithoutMedicine -> medicineEntity.getDrugstoreEntitySet()
                    .add(drugstoreConverter.convertToDboFromDtoWithoutMedicine(dtoWithoutMedicine)));
        }

//        medicineEntity.setOrderEntitySet(orderConverter.convertSetToDbo(dto.getOrderDtoSet()));
        return medicineEntity;
    }

    /*public Set<MedicineDto> convertSetToDto(final Set<MedicineEntity> entity) {
        if (entity != null) {
            final Set<MedicineDto> dtoSet = new HashSet<>();
            for (final MedicineEntity medicineEntity : entity) {
                final MedicineDto convertToDto = convertToDto(medicineEntity);
                dtoSet.add(convertToDto);
            }
            return dtoSet;
        } else {
            return null;
        }
    }

    public Set<MedicineEntity> convertSetToDbo(final Set<MedicineDto> dto) {
        if (dto != null) {
            final Set<MedicineEntity> entitySet = new HashSet<>();
            for (final MedicineDto medicineDto : dto) {
                final MedicineEntity convertToEntity = convertToDbo(medicineDto);
                entitySet.add(convertToEntity);
            }
            return entitySet;
        } else {
            return null;
        }
    }*/
}
