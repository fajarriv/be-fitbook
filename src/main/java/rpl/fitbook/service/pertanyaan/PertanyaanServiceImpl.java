package rpl.fitbook.service.pertanyaan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rpl.fitbook.model.pertanyaan.PertanyaanModel;
import rpl.fitbook.repository.pertanyaan.PertanyaanRepository;

import java.util.Optional;

@Service
public class PertanyaanServiceImpl implements PertanyaanService {

    private final PertanyaanRepository pertanyaanRepository;

    @Autowired
    public PertanyaanServiceImpl(PertanyaanRepository PertanyaanRepository) {
        this.pertanyaanRepository = PertanyaanRepository;
    }

    @Override
    public PertanyaanModel createPertanyaan(PertanyaanModel pertanyaan) {
        return pertanyaanRepository.save(pertanyaan);
    }

    @Override
    public PertanyaanModel answerPertanyaan(String pertanyaanId, String jawaban) {
        Optional<PertanyaanModel> existingPertanyaan = pertanyaanRepository.findById(pertanyaanId);

        if (existingPertanyaan.isPresent() && (existingPertanyaan.get().getJawaban() == null || existingPertanyaan.get().getJawaban().isEmpty())) {
            PertanyaanModel pertanyaanToUpdate = existingPertanyaan.get();
            pertanyaanToUpdate.setJawaban(jawaban);
            return pertanyaanRepository.save(pertanyaanToUpdate);
        } else {
            throw new IllegalStateException("Pertanyaan tidak ditemukan atau sudah dijawab.");
        }
    }
}
