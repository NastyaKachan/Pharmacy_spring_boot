package by.academy.pharmacy_spring_boot.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

public interface AbstractMapper<E, D> {
    E toEntity(D dto);

    D toDto(E entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    E updateEntityFromDto(D dto, @MappingTarget E entity);
}
