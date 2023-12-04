package rpl.fitbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import rpl.fitbook.dto.auth.LoginPengguna;
import rpl.fitbook.dto.auth.LoginResponse;
import rpl.fitbook.dto.auth.RegisterPengguna;
import rpl.fitbook.service.auth.AuthService;
import rpl.fitbook.util.ResponseUtil;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody RegisterPengguna data) {
        String token = authService.register(data);
        String message = String.format("Berhasil membuat akun %s", data.getEmail());
        return ResponseUtil.okResponse(token, message);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginPengguna data) {
        String token = authService.login(data);
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        return ResponseEntity.ok(response);
    }
}
