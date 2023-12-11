package rpl.fitbook.dto.pesanan;

import lombok.Getter;
import lombok.Setter;
import rpl.fitbook.dto.sesikelas.SesiKelasMini;
import rpl.fitbook.model.peasanan.PesananStatus;


@Getter
@Setter
public class PesananDto {
    
    private String id;

    private SesiKelasMini sesiKelas; 

    private PesananStatus status;
}
