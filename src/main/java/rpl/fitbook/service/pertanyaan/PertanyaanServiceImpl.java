package rpl.fitbook.service.pertanyaan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import rpl.fitbook.dto.pertanyaan.PertanyaanCreate;
import rpl.fitbook.exception.NotFoundException;
import rpl.fitbook.model.pengguna.UserModel;
import rpl.fitbook.model.pertanyaan.PertanyaanModel;
import rpl.fitbook.repository.pertanyaan.PertanyaanRepository;
import rpl.fitbook.service.pengguna.PenggunaService;
import rpl.fitbook.service.sesikelas.SesiKelasService;
import rpl.fitbook.service.user.UserService;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class PertanyaanServiceImpl implements PertanyaanService {

    @Autowired
    PertanyaanRepository pertanyaanRepository;

    @Autowired
    PenggunaService penggunaService;

    @Autowired
    UserService userService;
    
    @Autowired
    SesiKelasService sesiKelasService;

    @Override
    public PertanyaanModel getPertanyaanById(String id) {
        return pertanyaanRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Pertanyaan dengan id " + id + " tidak ditemukan"));
    }

    @Override
    public PertanyaanModel createPertanyaan(PertanyaanCreate data) {
        PertanyaanModel pertanyaan = new PertanyaanModel();
        
        // Get User Model from pengguna that sent the request
        UserModel user = userService.getUserById(penggunaService.getCurrentPenggunaId());
        
        pertanyaan.setPertanyaan(data.getPertanyaan());
        pertanyaan.setUser(user);
        pertanyaan.setTimestamp(LocalDateTime.now());
        pertanyaan.setSesiKelas(sesiKelasService.getSesiKelasById(data.getSesiKelasId()));

        return pertanyaanRepository.save(pertanyaan);
    }

    @Override
    public PertanyaanModel answerPertanyaan(String pertanyaanId, String jawaban) {
        Optional<PertanyaanModel> existingPertanyaan = pertanyaanRepository.findById(pertanyaanId);

        if (existingPertanyaan.isPresent()
                && (existingPertanyaan.get().getJawaban() == null || existingPertanyaan.get().getJawaban().isEmpty())) {
            PertanyaanModel pertanyaanToUpdate = existingPertanyaan.get();
            pertanyaanToUpdate.setJawaban(jawaban);
            return pertanyaanRepository.save(pertanyaanToUpdate);
        } else {
            throw new IllegalStateException("Pertanyaan tidak ditemukan atau sudah dijawab.");
        }
    }

}
