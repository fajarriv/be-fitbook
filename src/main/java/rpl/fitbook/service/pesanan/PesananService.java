package rpl.fitbook.service.pesanan;

import rpl.fitbook.model.peasanan.PesananModel;
import rpl.fitbook.model.pengguna.UserModel;

import java.util.List;

public interface PesananService {

    PesananModel getPesananById(String idPesanan);

    PesananModel createPesanan(String idKelas);

    List<PesananModel>getAllPesananUserByStatus(String status);

    void cancelPesanan(String idPesanan);
}
