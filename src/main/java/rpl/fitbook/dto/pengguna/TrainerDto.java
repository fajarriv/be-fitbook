package rpl.fitbook.dto.pengguna;

import lombok.Getter;
import lombok.Setter;
import rpl.fitbook.model.pengguna.Role;

@Getter
@Setter
public class TrainerDto {
    
    private String id;
    private Role role;
    private String name;
    private String email;
    private String displayName;
    private String noTelp;
    private String bio;
    private Float rating;
}
