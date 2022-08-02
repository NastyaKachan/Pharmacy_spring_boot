package by.academy.pharmacy_spring_boot.mapper;

import by.academy.pharmacy_spring_boot.dto.ProductDto;
import by.academy.pharmacy_spring_boot.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper extends AbstractMapper<Product, ProductDto> {

}
