package by.academy.pharmacy_spring_boot.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Data
public class UserDto implements Serializable {
    private final Integer id;
    @NotBlank(message = "Поле не должно быть пустым")
    @Size(min = 3, max = 16, message = "Длина login должна быть от 3 до 16 символов")
    @Pattern(regexp = "[a-zA-Z]*", message = "Только латинские буквы")
    private final String login;
    private final String password;
    private final Set<RoleDto> roles;

    @Data
    public static class RoleDto implements Serializable {
        private final Integer id;
        private final String name;
    }
}
