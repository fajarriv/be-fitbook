package rpl.fitbook.model.pengguna;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rpl.fitbook.model.sesikelas.SesiKelasModel;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TrainerModel extends PenggunaModel {
    private String bio;
    private Float rating;

    @OneToMany(mappedBy = "trainer", fetch = FetchType.LAZY)
    private List<SesiKelasModel> listSesiKelas;
}

