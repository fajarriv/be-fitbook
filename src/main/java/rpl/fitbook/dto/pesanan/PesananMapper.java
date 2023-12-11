package rpl.fitbook.dto.pesanan;

import org.springframework.beans.BeanUtils;

import rpl.fitbook.dto.sesikelas.SesiKelasMapper;
import rpl.fitbook.dto.sesikelas.SesiKelasMini;
import rpl.fitbook.model.peasanan.PesananModel;

public class PesananMapper {

    public static PesananDto toDto(PesananModel model) {
        PesananDto pesanan = new PesananDto();
        
        SesiKelasMini sesiKelas = new SesiKelasMini();
        BeanUtils.copyProperties(model.getSesiKelas(), sesiKelas);

        pesanan.setId(model.getId());
        pesanan.setSesiKelas(sesiKelas);
        pesanan.setStatus(model.getStatus());
        return pesanan;
    }
}
