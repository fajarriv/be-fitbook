package rpl.fitbook.model.pertanyaan;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import rpl.fitbook.model.pengguna.UserModel;
import rpl.fitbook.model.sesikelas.SesiKelasModel;

@Getter
@Setter
@Entity
@Table(name = "pertanyaan")
public class PertanyaanModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String pertanyaan;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sesi_kelas_id", nullable = false)
    private SesiKelasModel sesiKelas;

    @Nullable
    public String jawaban;
}
