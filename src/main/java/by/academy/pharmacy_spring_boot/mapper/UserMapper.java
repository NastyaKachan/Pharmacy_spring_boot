package by.academy.pharmacy_spring_boot.mapper;

import by.academy.pharmacy_spring_boot.dto.UserDto;
import by.academy.pharmacy_spring_boot.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends AbstractMapper<User, UserDto> {

}
