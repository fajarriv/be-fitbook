package rpl.fitbook.service.pertanyaan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import rpl.fitbook.dto.pertanyaan.JawabanCreate;
import rpl.fitbook.dto.pertanyaan.PertanyaanCreate;
import rpl.fitbook.exception.BadRequestException;
import rpl.fitbook.exception.NotFoundException;
import rpl.fitbook.model.pengguna.UserModel;
import rpl.fitbook.model.pertanyaan.PertanyaanModel;
import rpl.fitbook.repository.pertanyaan.PertanyaanRepository;
import rpl.fitbook.service.pengguna.PenggunaService;
import rpl.fitbook.service.sesikelas.SesiKelasService;
import rpl.fitbook.service.user.UserService;
import java.time.LocalDateTime;

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
    public PertanyaanModel answerPertanyaan(String pertanyaanId, JawabanCreate jawaban) {
        PertanyaanModel pertanyaan = getPertanyaanById(pertanyaanId);

        // Get pengguna id from pengguna that sent the request
        String penggunaId = penggunaService.getCurrentPenggunaId();
        
        // Check if pengguna is the trainer of the class
        if (!pertanyaan.getSesiKelas().getTrainer().getId().equals(penggunaId)) {
            throw new BadRequestException("Pengguna bukan trainer dari kelas ini");
        }

        pertanyaan.setJawaban(jawaban.getJawaban());
        return pertanyaanRepository.save(pertanyaan);
    }

}
