package rpl.fitbook.controller.pertanyaan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rpl.fitbook.model.pertanyaan.PertanyaanModel;
import rpl.fitbook.service.pertanyaan.PertanyaanService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://fitbookit.net"})
@RequestMapping("/pertanyaan")
public class PertanyaanController {

    private final PertanyaanService pertanyaanService;

    @Autowired
    public PertanyaanController(PertanyaanService pertanyaanService) {
        this.pertanyaanService = pertanyaanService;
    }

    @GetMapping("/pertanyaan")
    @PostMapping
    public ResponseEntity<PertanyaanModel> createPertanyaan(@RequestBody PertanyaanModel pertanyaan) {
        PertanyaanModel createdPertanyaan = pertanyaanService.createPertanyaan(pertanyaan);
        return ResponseEntity.ok(createdPertanyaan);
    }

    // Endpoint to answer a question
    @PutMapping("/{id}/jawaban")
    public ResponseEntity<PertanyaanModel> answerPertanyaan(@PathVariable String id, @RequestBody String jawaban) {
        try {
            PertanyaanModel updatedPertanyaan = pertanyaanService.answerPertanyaan(id, jawaban);
            return ResponseEntity.ok(updatedPertanyaan);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
