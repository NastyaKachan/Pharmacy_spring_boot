package by.academy.pharmacy_spring_boot.mapper;

import by.academy.pharmacy_spring_boot.dto.MnnDto;
import by.academy.pharmacy_spring_boot.entity.MNN;
import org.mapstruct.Mapper;

import javax.xml.validation.Validator;

@Mapper(componentModel = "spring", uses = {ProductMapper.class, Validator.class})
public interface MnnMapper {

    MNN toEntity(MnnDto mnnDto);

    MnnDto toDto(MNN mnn);
}
