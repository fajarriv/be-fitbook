package rpl.fitbook.dto.pengguna;

import rpl.fitbook.model.pengguna.TrainerModel;
import rpl.fitbook.model.pengguna.UserModel;

public class UserMapper {

    public static UserDto toDto(UserModel model) {
        UserDto dto = new UserDto();
        dto.setId(model.getId());
        dto.setRole(model.getRole());
        dto.setName(model.getName());
        dto.setEmail(model.getEmail());
        dto.setDisplayName(model.getDisplayName());
        dto.setNoTelp(model.getNoTelp());
        return dto;
    }
}
