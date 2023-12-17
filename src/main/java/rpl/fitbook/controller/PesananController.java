package rpl.fitbook.controller;

// import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import rpl.fitbook.dto.pesanan.PesananMapper;
import rpl.fitbook.model.peasanan.PesananModel;
import rpl.fitbook.service.pesanan.PesananService;
import rpl.fitbook.util.ResponseUtil;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://fitbookit.net"})
@RequestMapping("/api")
public class PesananController {
    @Autowired
    PesananService pesananService;

    @PostMapping("/pesanan/{sesiKelasId}")
    @PreAuthorize("hasAuthority('User')")
    public ResponseEntity<Object> create(@PathVariable(value = "sesiKelasId") String sesiKelasId) {
        PesananModel createdKelas = pesananService.createPesanan(sesiKelasId);
        String message = String.format("Berhasil membuat pesanan untuk sesi kelas %s",
                createdKelas.getSesiKelas().getJudul());
        return ResponseUtil.okResponse(PesananMapper.toDto(createdKelas), message);
    }

}