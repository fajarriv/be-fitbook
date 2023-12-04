package rpl.fitbook.model.pengguna;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import rpl.fitbook.model.peasanan.PesananModel;

import java.util.List;


@Getter
@Setter
@Entity
public class UserModel extends PenggunaModel {

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<PesananModel> daftarPesanan;
}
