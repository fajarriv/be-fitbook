package rpl.fitbook.service.trainer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rpl.fitbook.model.pengguna.TrainerModel;
import rpl.fitbook.repository.pengguna.TrainerRepository;

@Service
@RequiredArgsConstructor
public class TrainerServiceImpl implements TrainerService{

    private final TrainerRepository trainerRepository;

    @Override
    public void rateTrainer(Long trainerId, Float rating){

    }

    @Override
    @Transactional(readOnly = true)
    public TrainerModel getTrainerBySesiKelasId(String sesiKelasId) {
        return trainerRepository.findBySesiKelasId(sesiKelasId);
    }

}
