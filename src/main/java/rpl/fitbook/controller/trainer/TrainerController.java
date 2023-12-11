package rpl.fitbook.controller.trainer;

import org.springframework.security.access.prepost.PreAuthorize;
import rpl.fitbook.dto.sesikelas.SesiKelasMapper;
import rpl.fitbook.model.sesikelas.SesiKelasModel;
import rpl.fitbook.service.sesikelas.SesiKelasService;
import rpl.fitbook.service.trainer.TrainerService;
import rpl.fitbook.model.pengguna.TrainerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rpl.fitbook.util.ResponseUtil;

import java.util.List;

@RestController
@RequestMapping("/api/trainer")
public class TrainerController {

    @Autowired
    SesiKelasService sesiKelasService;
    @Autowired
    private TrainerService trainerService;

    @PostMapping("/{trainerId}/rate")
    public ResponseEntity<?> rateTrainer(@PathVariable Long trainerId, @RequestBody Float rating) {
        trainerService.rateTrainer(trainerId, rating);
        return ResponseEntity.ok("Trainer rated successfully.");
    }

//    @GetMapping("/sesi-kelas/{sesiKelasId}/trainer")
//    public ResponseEntity<TrainerModel> getTrainerBySesiKelasId(@PathVariable String sesiKelasId) {
//        TrainerModel trainer = trainerService.getTrainerBySesiKelasId(sesiKelasId);
//
//        if (trainer == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        return ResponseEntity.ok(trainer);
//    }

    @GetMapping("/sesi-kelas/{status}")
    @PreAuthorize("hasAuthority('Trainer')")
    public ResponseEntity<Object> getDashboard(@PathVariable(value = "status") String status) {
        List<SesiKelasModel> kelasTerbuat = sesiKelasService.getDashboardTrainer(status);
        return ResponseUtil.okResponse(SesiKelasMapper.toHomePageDto(kelasTerbuat), "success");
    }


    @PutMapping("/{email}/updateBio")
    public ResponseEntity<?> updateTrainerBio(@PathVariable String email, @RequestBody String newBio) {
        TrainerModel updatedTrainer = trainerService.updateTrainerBio(email, newBio);

        if (updatedTrainer == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Trainer's bio updated successfully.");
    }
}
