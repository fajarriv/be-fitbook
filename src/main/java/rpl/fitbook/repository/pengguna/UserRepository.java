package rpl.fitbook.repository.pengguna;

import org.springframework.data.jpa.repository.JpaRepository;

import com.google.common.base.Optional;

import rpl.fitbook.model.pengguna.PenggunaModel;
import rpl.fitbook.model.pengguna.UserModel;

public interface UserRepository extends JpaRepository<UserModel, String> {

    Optional<PenggunaModel> findByEmail(String email);
}
