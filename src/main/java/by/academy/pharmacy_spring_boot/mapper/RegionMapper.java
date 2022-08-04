package by.academy.pharmacy_spring_boot.mapper;

import by.academy.pharmacy_spring_boot.dto.RegionDto;
import by.academy.pharmacy_spring_boot.entity.Region;
import org.mapstruct.Mapper;

import javax.xml.validation.Validator;

@Mapper(componentModel = "spring", uses = {Validator.class})
public interface RegionMapper {

    Region toEntity(RegionDto regionDto);

    RegionDto toDto(Region region);
}
