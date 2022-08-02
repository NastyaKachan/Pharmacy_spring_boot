package by.academy.pharmacy_spring_boot.mapper;

import by.academy.pharmacy_spring_boot.dto.PharmacyDto;
import by.academy.pharmacy_spring_boot.entity.Pharmacy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PharmacyMapper extends AbstractMapper<Pharmacy, PharmacyDto> {

}
