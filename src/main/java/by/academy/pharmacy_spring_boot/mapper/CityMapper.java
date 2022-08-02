package by.academy.pharmacy_spring_boot.mapper;

import by.academy.pharmacy_spring_boot.dto.CityDto;
import by.academy.pharmacy_spring_boot.entity.City;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CityMapper extends AbstractMapper<City, CityDto> {

}
