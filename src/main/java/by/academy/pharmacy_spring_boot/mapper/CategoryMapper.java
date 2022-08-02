package by.academy.pharmacy_spring_boot.mapper;

import by.academy.pharmacy_spring_boot.dto.CategoryDto;
import by.academy.pharmacy_spring_boot.entity.Category;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends AbstractMapper<Category, CategoryDto> {
    @AfterMapping
    default void linkProducts(@MappingTarget Category category) {
        category.getProducts().forEach(product -> product.setCategory(category));
    }
}
