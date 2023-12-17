package rpl.fitbook.controller.pertanyaan;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import rpl.fitbook.dto.pertanyaan.PertanyaanCreate;
import rpl.fitbook.model.pertanyaan.PertanyaanModel;
import rpl.fitbook.service.pertanyaan.PertanyaanService;
import rpl.fitbook.util.ResponseUtil;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://fitbookit.net"})
@RequestMapping("/api")
public class PertanyaanController {

    @Autowired
    PertanyaanService pertanyaanService;

    @PostMapping("/pertanyaan")    
    @PreAuthorize("hasAuthority('User')")
    public ResponseEntity<Object> createPertanyaan(@RequestBody PertanyaanCreate pertanyaan) {
        PertanyaanModel createdPertanyaan = pertanyaanService.createPertanyaan(pertanyaan);
        return ResponseUtil.okResponse(createdPertanyaan, "success");
    }

    // Endpoint to answer a question
    @PutMapping("/{id}/jawaban")
    public ResponseEntity<Object> answerPertanyaan(@PathVariable String id, @RequestBody String jawaban) {
        try {
            PertanyaanModel updatedPertanyaan = pertanyaanService.answerPertanyaan(id, jawaban);
            return ResponseEntity.ok(updatedPertanyaan);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
