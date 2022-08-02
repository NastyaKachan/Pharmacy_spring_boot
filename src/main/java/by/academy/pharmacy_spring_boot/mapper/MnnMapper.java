package by.academy.pharmacy_spring_boot.mapper;

import by.academy.pharmacy_spring_boot.dto.MnnDto;
import by.academy.pharmacy_spring_boot.entity.MNN;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MnnMapper extends AbstractMapper<MNN, MnnDto> {
    @AfterMapping
    default void linkProducts(@MappingTarget MNN mnn) {
        mnn.getProducts().forEach(product -> product.setMnn(mnn));
    }
}
