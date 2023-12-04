package rpl.fitbook.repository.pertanyaan;

import org.springframework.data.jpa.repository.JpaRepository;
import rpl.fitbook.model.pertanyaan.PertanyaanModel;
import java.util.Optional;

public  interface PertanyaanRepository extends JpaRepository<PertanyaanModel, String> {

    Optional<PertanyaanModel> findById(String id);

}

