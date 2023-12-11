package rpl.fitbook.service.sesikelas;

import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import rpl.fitbook.dto.sesikelas.SesiKelasCreate;
import rpl.fitbook.exception.NotFoundException;
import rpl.fitbook.model.pengguna.TrainerModel;
import rpl.fitbook.model.sesikelas.SesiKelasModel;
import rpl.fitbook.model.sesikelas.SesiKelasStatus;
import rpl.fitbook.repository.sesikelas.SesiKelasRepository;
import rpl.fitbook.service.pengguna.PenggunaService;
import rpl.fitbook.service.trainer.TrainerService;

import java.time.format.*;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class SesiKelasServiceImpl implements SesiKelasService {

    @Autowired
    SesiKelasRepository sesiKelasRepo;

    @Autowired
    PenggunaService penggunaService;

    @Autowired
    TrainerService trainerService;


    @Override
    public SesiKelasModel getSesiKelasById(String id) {
        SesiKelasModel sesiKelas = sesiKelasRepo.findById(id).orElseThrow(
                () -> new NotFoundException("Sesi Kelas dengan id " + id + " tidak ditemukan"));
        return sesiKelas;
    }

    @Override
    public SesiKelasModel createSesiKelas(SesiKelasCreate form) {
        SesiKelasModel newKelas = new SesiKelasModel();
        BeanUtils.copyProperties(form, newKelas, "jadwalWaktu");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime dateTimeJadwal = LocalDateTime.parse(form.getJadwalWaktu(), formatter);

        newKelas.setJadwalWaktu(dateTimeJadwal);

        // Get trainer model form pengguna that sent the request
        TrainerModel trainer = trainerService.getTrainerById(penggunaService.getCurrentPenggunaId());
        
        newKelas.setTrainer(trainer);
        newKelas.setStatus(SesiKelasStatus.Ongoing);
        newKelas.setCurrentParticipant(0);
        
        return sesiKelasRepo.save(newKelas);
    }

    @Override
    public List<SesiKelasModel> getAllSesiKelas() {
        return sesiKelasRepo.findAllByStatus(SesiKelasStatus.Ongoing);
    }

    public List<SesiKelasModel> getDashboardTrainer(String status) {
        // Get trainer model form pengguna that sent the request
        TrainerModel trainer = trainerService.getTrainerById(penggunaService.getCurrentPenggunaId());
        return sesiKelasRepo.findAllByTrainerAndStatus(trainer, SesiKelasStatus.valueOf(status));
    }

}
