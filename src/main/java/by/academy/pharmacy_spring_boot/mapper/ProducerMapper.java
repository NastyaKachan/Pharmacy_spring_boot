package by.academy.pharmacy_spring_boot.mapper;

import by.academy.pharmacy_spring_boot.dto.ProducerDto;
import by.academy.pharmacy_spring_boot.entity.Producer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProducerMapper {

    Producer toEntity(ProducerDto producerDto);

    ProducerDto toDto(Producer producer);
}
