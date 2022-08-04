package by.academy.pharmacy_spring_boot.mapper;

import by.academy.pharmacy_spring_boot.dto.CityDto;
import by.academy.pharmacy_spring_boot.entity.City;
import org.mapstruct.Mapper;

import javax.xml.validation.Validator;

@Mapper(componentModel = "spring", uses = {PharmacyMapper.class, RegionMapper.class, Validator.class})
public interface CityMapper {

    City toEntity(CityDto cityDto);

    CityDto toDto(City city);
}
