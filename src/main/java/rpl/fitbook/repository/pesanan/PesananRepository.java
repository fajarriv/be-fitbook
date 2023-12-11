package rpl.fitbook.repository.pesanan;

import org.springframework.data.jpa.repository.JpaRepository;

import rpl.fitbook.model.peasanan.PesananModel;

public interface PesananRepository extends JpaRepository<PesananModel, String>{

    
}
