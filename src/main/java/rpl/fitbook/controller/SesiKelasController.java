package rpl.fitbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import rpl.fitbook.dto.sesikelas.SesiKelasCreate;
import rpl.fitbook.dto.sesikelas.SesiKelasMapper;
import rpl.fitbook.model.sesikelas.SesiKelasModel;
import rpl.fitbook.service.sesikelas.SesiKelasService;
import rpl.fitbook.util.ResponseUtil;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
public class SesiKelasController {
    @Autowired
    SesiKelasService sesiKelasService;

    @PostMapping("/sesi-kelas")
    @PreAuthorize("hasAuthority('Trainer')")
    public ResponseEntity<Object> create(@Valid @RequestBody SesiKelasCreate data) {
        SesiKelasModel createdKelas = sesiKelasService.createSesiKelas(data);
        String message = String.format("Berhasil membuat sesi kelas %s", data.getJudul());
        return ResponseUtil.okResponse(SesiKelasMapper.toDto(createdKelas), message);
    }

    @PostMapping("/sesi-kelas/{id}")
    @PreAuthorize("hasAnyAuthority('Admin', 'Trainer', 'User')")
    public ResponseEntity<Object> getDetailKelas(@PathVariable(value = "id") String idKelas) {
        SesiKelasModel createdKelas = sesiKelasService.getSesiKelasById(idKelas);
        return ResponseUtil.okResponse(SesiKelasMapper.toDto(createdKelas), "success");
    }
}
