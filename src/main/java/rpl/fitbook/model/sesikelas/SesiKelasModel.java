package rpl.fitbook.model.sesikelas;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import rpl.fitbook.model.pengguna.TrainerModel;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "sesi_kelas")
public class SesiKelasModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String judul;

    private String deskripsi;

    // maybe broken
    @ManyToOne
    @JoinColumn(name = "trainer_id", nullable = false)
    private TrainerModel trainer;

    private LocalDateTime jadwalWaktu;

    private String lokasi;

    private Integer maxParticipant;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private Integer currentParticipant;

    private String noTelp;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SesiKelasStatus status;
}
