package by.academy.pharmacy_spring_boot.mapper;

import by.academy.pharmacy_spring_boot.dto.PharmacyDto;
import by.academy.pharmacy_spring_boot.entity.Pharmacy;
import org.mapstruct.Mapper;

import javax.xml.validation.Validator;

@Mapper(componentModel = "spring", uses = {PharmacyChainMapper.class, CityMapper.class, Validator.class})
public interface PharmacyMapper {

    Pharmacy toEntity(PharmacyDto pharmacyDto);

    PharmacyDto toDto(Pharmacy pharmacy);
}
