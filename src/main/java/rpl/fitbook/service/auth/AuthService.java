package rpl.fitbook.service.auth;

import rpl.fitbook.dto.auth.LoginPengguna;
import rpl.fitbook.dto.auth.RegisterPengguna;

public interface AuthService {

    public String register(RegisterPengguna request);

    public String login(LoginPengguna request);
}
