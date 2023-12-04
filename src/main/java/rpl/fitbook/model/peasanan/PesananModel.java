package rpl.fitbook.model.peasanan;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import rpl.fitbook.model.pengguna.UserModel;
import rpl.fitbook.model.sesikelas.SesiKelasModel;

@Getter
@Setter
@Entity
@Table(name = "pesanan")
public class PesananModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user; 

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sesi_kelas_id", nullable = false)
    private SesiKelasModel sesiKelas; 

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PesananStatus status;
}
