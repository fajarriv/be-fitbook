package rpl.fitbook.service.trainer;

import rpl.fitbook.model.pengguna.TrainerModel;

public interface TrainerService {
    void rateTrainer(Long trainerId, Float rating);

    TrainerModel getTrainerBySesiKelasId(String sesiKelasId);

    TrainerModel getTrainerById(String id);
    TrainerModel updateTrainerBio(String email, String newBio);
}
