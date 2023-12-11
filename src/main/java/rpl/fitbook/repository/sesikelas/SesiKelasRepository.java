package rpl.fitbook.repository.sesikelas;

import org.springframework.data.jpa.repository.JpaRepository;

import rpl.fitbook.model.pengguna.TrainerModel;
import rpl.fitbook.model.sesikelas.SesiKelasModel;
import rpl.fitbook.model.sesikelas.SesiKelasStatus;

import java.util.List;

public interface SesiKelasRepository extends JpaRepository<SesiKelasModel, String>{
    List<SesiKelasModel> findAllByTrainerAndStatus(TrainerModel trainer, SesiKelasStatus status);

    List<SesiKelasModel> findAllByStatus(SesiKelasStatus status);
}
