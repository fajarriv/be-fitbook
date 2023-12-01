package rpl.fitbook.repository.pengguna;

import rpl.fitbook.model.pengguna.PenggunaModel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PenggunaRepository extends JpaRepository<PenggunaModel, String> {
    Optional<PenggunaModel> findByEmail(String email);
}
