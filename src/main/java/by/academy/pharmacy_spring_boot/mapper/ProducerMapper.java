package by.academy.pharmacy_spring_boot.mapper;

import by.academy.pharmacy_spring_boot.dto.ProducerDto;
import by.academy.pharmacy_spring_boot.entity.Producer;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProducerMapper extends AbstractMapper<Producer, ProducerDto> {
    @AfterMapping
    default void linkProduct(@MappingTarget Producer producer) {
        producer.getProducts().forEach(product -> product.setProducer(producer));
    }
}
