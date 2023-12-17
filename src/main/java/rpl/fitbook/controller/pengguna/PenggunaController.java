package rpl.fitbook.controller.pengguna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rpl.fitbook.dto.updateProfile.UpdateTrainerRequest;
import rpl.fitbook.service.pengguna.PenggunaService;
import rpl.fitbook.model.pengguna.PenggunaModel;

@RestController
@RequestMapping("/api/pengguna/")
public class PenggunaController {

    @Autowired
    PenggunaService penggunaService;

    @PutMapping("/{email}/updateProfile")
    public ResponseEntity<?> updatePenggunaProfile(@PathVariable String email, @RequestBody UpdateTrainerRequest request) {
        PenggunaModel updatedPengguna = penggunaService.updateProfile(email, request.getNewDisplayName(), request.getNewNoTelp());

        if (updatedPengguna == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Pengguna Display Name and No Telp updated successfully.");
    }

}
