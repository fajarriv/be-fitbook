package rpl.fitbook.controller.pertanyaan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import rpl.fitbook.dto.pertanyaan.JawabanCreate;
import rpl.fitbook.dto.pertanyaan.PertanyaanCreate;
import rpl.fitbook.model.pertanyaan.PertanyaanModel;
import rpl.fitbook.service.pertanyaan.PertanyaanService;
import rpl.fitbook.util.ResponseUtil;

@RestController
@CrossOrigin(origins = { "http://localhost:3000", "https://fitbookit.net" })
@RequestMapping("/api")
public class PertanyaanController {

    @Autowired
    PertanyaanService pertanyaanService;

    @PostMapping("/pertanyaan")
    @PreAuthorize("hasAuthority('User')")
    public ResponseEntity<Object> createPertanyaan(@Valid @RequestBody PertanyaanCreate pertanyaan) {
        PertanyaanModel createdPertanyaan = pertanyaanService.createPertanyaan(pertanyaan);
        return ResponseUtil.okResponse(null, "success");
    }

    // Endpoint to answer a question
    @PostMapping("/pertanyaan/{pertanyaanId}")
    @PreAuthorize("hasAuthority('Trainer')")
    public ResponseEntity<Object> answerPertanyaan(@PathVariable(value = "pertanyaanId") String pertanyaanId,
            @RequestBody JawabanCreate jawaban) {
        PertanyaanModel pertanyaan = pertanyaanService.answerPertanyaan(pertanyaanId, jawaban);
        return ResponseUtil.okResponse(null, "success");
    }
}
