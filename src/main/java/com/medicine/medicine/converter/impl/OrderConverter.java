package com.medicine.medicine.converter.impl;

import com.medicine.medicine.converter.DtoDboConverter;
import com.medicine.medicine.dto.OrderDto;
import com.medicine.medicine.entity.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class OrderConverter implements DtoDboConverter<OrderDto, OrderEntity> {
    private MedicineConverter medicineConverter;

    @Override
    public OrderDto convertToDto(final OrderEntity entity) {
        final OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(entity, orderDto);
        orderDto.setMedicineDtoSet(medicineConverter.convertSetToDto(entity.getMedicineEntitySet()));
        return orderDto;
    }

    @Override
    public OrderEntity convertToDbo(final OrderDto dto) {
        final OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(dto, orderEntity);
        orderEntity.setMedicineEntitySet(medicineConverter.convertSetToDbo(dto.getMedicineDtoSet()));
        return orderEntity;
    }

    public OrderEntity convertToDboForCreate(final OrderDto dto) {
        final OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(dto, orderEntity, "creationDate");
        orderEntity.setMedicineEntitySet(medicineConverter.convertSetToDbo(dto.getMedicineDtoSet()));
        return orderEntity;
    }

    /*public Set<OrderDto> convertSetToDto(final Set<OrderEntity> entity) {
        if (entity != null) {
            final Set<OrderDto> dtoSet = new HashSet<>();
            for (final OrderEntity orderEntity : entity) {
                final OrderDto convertToDto = convertToDto(orderEntity);
                dtoSet.add(convertToDto);
            }
            return dtoSet;
        } else {
            return null;
        }
    }

    public Set<OrderEntity> convertSetToDbo(final Set<OrderDto> dto) {
        if (dto != null) {
            final Set<OrderEntity> entitySet = new HashSet<>();
            for (final OrderDto orderDto : dto) {
                final OrderEntity convertToEntity = convertToDbo(orderDto);
                entitySet.add(convertToEntity);
            }
            return entitySet;
        } else {
            return null;
        }
    }*/
}
