package rpl.fitbook.service.sesikelas;

import rpl.fitbook.dto.sesikelas.SesiKelasCreate;
import rpl.fitbook.model.sesikelas.SesiKelasModel;

public interface SesiKelasService {

    SesiKelasModel getSesiKelasById(String id);

    SesiKelasModel createSesiKelas(SesiKelasCreate form);
}
