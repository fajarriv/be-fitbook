package rpl.fitbook.controller.trainer;

import rpl.fitbook.service.trainer.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trainers")
public class TrainerController {

    @Autowired
    private TrainerService trainerService;

    @PostMapping("/{trainerId}/rate")
    public ResponseEntity<?> rateTrainer(@PathVariable Long trainerId, @RequestBody Float rating) {
        trainerService.rateTrainer(trainerId, rating);
        return ResponseEntity.ok("Trainer rated successfully.");
    }

}
