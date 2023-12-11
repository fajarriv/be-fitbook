package rpl.fitbook.service.sesikelas;

import rpl.fitbook.dto.sesikelas.SesiKelasCreate;
import rpl.fitbook.model.sesikelas.SesiKelasModel;

import java.util.List;

public interface SesiKelasService {

    SesiKelasModel getSesiKelasById(String id);

    SesiKelasModel createSesiKelas(SesiKelasCreate form);

    List<SesiKelasModel> getAllSesiKelas();
    List<SesiKelasModel> getDashboardTrainer(String status);
}
