package rpl.fitbook.repository;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import rpl.fitbook.model.laporan.Laporan;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface LaporanRepository extends JpaRepository<Laporan, Integer> {
    @NonNull
    Optional<Laporan> findById(@NonNull Integer id);

    @NonNull
    Optional<Laporan>  findByPelapor(@NonNull String pelapor);

    void deleteById(@NonNull Integer id);

    void deleteByPelapor(@NonNull String pelapor);
}

