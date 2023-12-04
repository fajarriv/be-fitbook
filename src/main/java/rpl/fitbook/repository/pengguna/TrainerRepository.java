package rpl.fitbook.repository.pengguna;

import org.springframework.data.jpa.repository.JpaRepository;

import com.google.common.base.Optional;

import rpl.fitbook.model.pengguna.PenggunaModel;
import rpl.fitbook.model.pengguna.TrainerModel;

public interface TrainerRepository extends JpaRepository<TrainerModel, String> {

    Optional<PenggunaModel> findByEmail(String email);
}
