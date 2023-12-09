package rpl.fitbook.dto.sesikelas;

import lombok.Getter;
import lombok.Setter;
import rpl.fitbook.dto.pengguna.TrainerDto;
import rpl.fitbook.model.sesikelas.SesiKelasStatus;

import java.time.LocalDateTime;


@Getter
@Setter
public class SesiKelasDto {
    private String id;
    private String judul;
    private String deskripsi;
    private TrainerDto trainer;
    private LocalDateTime jadwalWaktu;
    private String lokasi;
    private Integer maxParticipant;
    private Integer currentParticipant;
    private SesiKelasStatus status;
}

