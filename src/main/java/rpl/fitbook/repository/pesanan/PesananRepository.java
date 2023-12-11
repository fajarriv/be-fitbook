package rpl.fitbook.repository.pesanan;

import org.springframework.data.jpa.repository.JpaRepository;

import rpl.fitbook.model.peasanan.PesananModel;
import rpl.fitbook.model.peasanan.PesananStatus;
import rpl.fitbook.model.pengguna.UserModel;

import java.util.List;

public interface PesananRepository extends JpaRepository<PesananModel, String> {

    List<PesananModel> findAllByUserAndStatus(UserModel user, PesananStatus status);
}
