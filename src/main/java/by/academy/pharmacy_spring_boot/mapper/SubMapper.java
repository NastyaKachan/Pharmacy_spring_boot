package by.academy.pharmacy_spring_boot.mapper;

import by.academy.pharmacy_spring_boot.dto.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubMapper {
    ProductDto.ProducerDto toProducerDto(ProducerDto producerDto);
    ProductDto.MnnDto toMnnDto(MnnDto mnnDto);
    ProductDto.CategoryDto toCategoryDto(CategoryDto categoryDto);
    ProductDto.PharmacyDto toPharmacyDto(PharmacyDto pharmacyDto);
}
