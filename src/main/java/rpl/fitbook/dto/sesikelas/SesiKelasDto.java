package rpl.fitbook.dto.sesikelas;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import rpl.fitbook.dto.pengguna.TrainerDto;
import rpl.fitbook.model.pertanyaan.PertanyaanModel;
import rpl.fitbook.model.sesikelas.SesiKelasStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class SesiKelasDto {
    private String id;
    private String judul;
    private String deskripsi;
    private TrainerDto trainer;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime jadwalWaktu;
    private String lokasi;
    private Integer maxParticipant;
    private Integer currentParticipant;
    private SesiKelasStatus status;
    private List<PertanyaanModel> listPertanyaan;
}