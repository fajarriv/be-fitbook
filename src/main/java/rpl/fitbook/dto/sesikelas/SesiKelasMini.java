package rpl.fitbook.dto.sesikelas;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import rpl.fitbook.dto.pengguna.TrainerDto;
import rpl.fitbook.model.sesikelas.SesiKelasStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class SesiKelasMini {
    private String id;
    private String judul;
    private String deskripsi;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime jadwalWaktu;
    private Integer maxParticipant;
    private Integer currentParticipant;
}

