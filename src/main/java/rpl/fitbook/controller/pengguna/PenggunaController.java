package rpl.fitbook.controller.pengguna;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rpl.fitbook.service.pengguna.PenggunaService;
import rpl.fitbook.model.pengguna.PenggunaModel;

@RestController
@RequestMapping("/api/pengguna/")
public class PenggunaController {

    PenggunaService penggunaService;

    @PutMapping("/{email}/updateProfile")
    public ResponseEntity<?> updatePenggunaProfile(@PathVariable String email, @RequestBody String newDisplayName, @RequestBody String newNoTelp) {
        PenggunaModel updatedPengguna = penggunaService.updateProfile(email, newDisplayName, newNoTelp);

        if (updatedPengguna == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Trainer's bio updated successfully.");
    }

}
