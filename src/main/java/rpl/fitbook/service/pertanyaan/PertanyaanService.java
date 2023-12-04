package rpl.fitbook.service.pertanyaan;

import rpl.fitbook.model.pertanyaan.PertanyaanModel;

public interface PertanyaanService {
    PertanyaanModel createPertanyaan(PertanyaanModel pertanyaan);
    PertanyaanModel answerPertanyaan(String pertanyaanId, String jawaban);
}
