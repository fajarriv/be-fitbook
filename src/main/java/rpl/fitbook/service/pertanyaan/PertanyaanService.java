package rpl.fitbook.service.pertanyaan;

import rpl.fitbook.dto.pertanyaan.PertanyaanCreate;
import rpl.fitbook.model.pertanyaan.PertanyaanModel;

public interface PertanyaanService {
    PertanyaanModel getPertanyaanById(String id);
    PertanyaanModel createPertanyaan(PertanyaanCreate pertanyaan);
    PertanyaanModel answerPertanyaan(String pertanyaanId, String jawaban);
}
