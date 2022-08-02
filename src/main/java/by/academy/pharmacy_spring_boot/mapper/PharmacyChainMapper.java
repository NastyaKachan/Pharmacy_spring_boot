package by.academy.pharmacy_spring_boot.mapper;

import by.academy.pharmacy_spring_boot.dto.PharmacyChainDto;
import by.academy.pharmacy_spring_boot.entity.PharmacyChain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PharmacyChainMapper extends AbstractMapper<PharmacyChain, PharmacyChainDto> {

}
