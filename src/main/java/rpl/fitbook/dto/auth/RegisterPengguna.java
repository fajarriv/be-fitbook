package rpl.fitbook.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterPengguna {

    @NotBlank(message = "name is required.")
    private String name;

    @NotBlank(message = "email is required.")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,}$", message = "Invalid email format")
    private String email;

    @NotBlank(message = "password is required.")
    private String password;

    @NotBlank(message = "telp is required.")
    private String noTelp;

    @NotBlank(message = "displayName is required.")
    private String displayName;

    @NotBlank(message = "role is required.")
    private String role;

    private String bio;
}
