package rpl.fitbook.repository.trainer;

import rpl.fitbook.model.pengguna.TrainerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainerRepository extends JpaRepository<TrainerModel, Long> {
}
