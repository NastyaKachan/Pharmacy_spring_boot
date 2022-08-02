package by.academy.pharmacy_spring_boot.mapper;

import by.academy.pharmacy_spring_boot.dto.RegionDto;
import by.academy.pharmacy_spring_boot.entity.Region;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegionMapper extends AbstractMapper<Region, RegionDto> {

}
