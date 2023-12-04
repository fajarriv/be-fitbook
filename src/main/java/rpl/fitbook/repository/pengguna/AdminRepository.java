package rpl.fitbook.repository.pengguna;

import org.springframework.data.jpa.repository.JpaRepository;

import com.google.common.base.Optional;

import rpl.fitbook.model.pengguna.AdminModel;
import rpl.fitbook.model.pengguna.PenggunaModel;

public interface AdminRepository extends JpaRepository<AdminModel, String> {

    Optional<PenggunaModel> findByEmail(String email);
}
 