package rpl.fitbook.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rpl.fitbook.dto.pengguna.UserMapper;
import rpl.fitbook.dto.pesanan.PesananDto;
import rpl.fitbook.dto.pesanan.PesananMapper;
import rpl.fitbook.dto.sesikelas.SesiKelasMapper;
import rpl.fitbook.dto.sesikelas.SesiKelasMini;
import rpl.fitbook.model.peasanan.PesananModel;
import rpl.fitbook.model.pengguna.UserModel;
import rpl.fitbook.model.sesikelas.SesiKelasModel;
import rpl.fitbook.service.pengguna.PenggunaService;
import rpl.fitbook.service.pesanan.PesananService;
import rpl.fitbook.service.user.UserService;
import rpl.fitbook.util.ResponseUtil;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    PenggunaService penggunaService;
    @Autowired
    UserService userService;

    @Autowired
    PesananService pesananService;

    @GetMapping("")
    @PreAuthorize("hasAuthority('User')")
    public ResponseEntity<Object> getProfile() {
        UserModel currentUser = userService.getUserById(penggunaService.getCurrentPenggunaId());
        return ResponseUtil.okResponse(UserMapper.toDto(currentUser), "success");
    }

    @GetMapping("/pesanan/{status}")
    @PreAuthorize("hasAuthority('User')")
    public ResponseEntity<Object> getDashboard(@PathVariable(value = "status") String status) {
        List<PesananDto> result = new ArrayList<>();
        List<PesananModel> daftarPesanan = pesananService.getAllPesananUserByStatus(status);

        for (PesananModel pesanan : daftarPesanan) {
            result.add(PesananMapper.toDto(pesanan));
        }
        String message = String.format("Berhasil mendapatkan daftar pesanan dengan status %s", status);
        return ResponseUtil.okResponse(result, message);
    }
}
