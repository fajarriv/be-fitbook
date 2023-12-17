package rpl.fitbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rpl.fitbook.service.pengguna.PenggunaService;
import rpl.fitbook.service.security.UserDetailsImpl;
import rpl.fitbook.util.ResponseUtil;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://fitbookit.net"})
@RequestMapping("/api")
public class DummyController {

    @Autowired
    PenggunaService penggunaService;

    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<Object> hello() {
        return ResponseUtil.okResponse("PING!!", "test");
    }

    @GetMapping("/current")
    @PreAuthorize("hasAnyAuthority('Admin', 'Trainer', 'User')")
    public ResponseEntity<Object> current() {
        UserDetailsImpl data = penggunaService.getCurrentPengguna();
        return ResponseUtil.okResponse(data, "test");
    }
}
