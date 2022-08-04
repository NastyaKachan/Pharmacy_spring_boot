package by.academy.pharmacy_spring_boot.mapper;

import by.academy.pharmacy_spring_boot.dto.PharmacyChainDto;
import by.academy.pharmacy_spring_boot.entity.PharmacyChain;
import org.mapstruct.Mapper;

import javax.xml.validation.Validator;

@Mapper(componentModel = "spring", uses = {Validator.class})
public interface PharmacyChainMapper {

    PharmacyChain toEntity(PharmacyChainDto pharmacyChainDto);

    PharmacyChainDto toDto(PharmacyChain pharmacyChain);
}
