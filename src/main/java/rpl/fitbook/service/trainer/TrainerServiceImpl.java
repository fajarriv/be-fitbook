package rpl.fitbook.service.trainer;

import rpl.fitbook.model.pengguna.TrainerModel;
import rpl.fitbook.repository.trainer.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TrainerServiceImpl implements TrainerService {

    @Autowired
    private TrainerRepository trainerRepository;

    @Override
    @Transactional
    public void rateTrainer(Long trainerId, Float rating) {
        TrainerModel trainer = trainerRepository.findById(trainerId)
                .orElseThrow(() -> new RuntimeException("Trainer not found"));

        trainer.addRating(rating);

        trainerRepository.save(trainer);
    }
}
