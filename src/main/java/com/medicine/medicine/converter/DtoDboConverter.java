package com.medicine.medicine.converter;

import java.util.Set;
import java.util.stream.Collectors;

public interface DtoDboConverter<T, B> {
    T convertToDto(final B entity);
    B convertToDbo(final T dto);

    default Set<T> convertSetToDto(final Set<B> entities) {
        if (entities != null && !entities.isEmpty()) {
            return entities.stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toSet());
        }
        return null;
    }

    default Set<B> convertSetToDbo(final Set<T> dtos){
        if (dtos != null && !dtos.isEmpty()) {
            return dtos.stream()
                    .map(this::convertToDbo)
                    .collect(Collectors.toSet());
        }
        return null;
    }
}
