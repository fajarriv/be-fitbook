package rpl.fitbook.service.laporan;

import rpl.fitbook.dto.laporan.LaporanRequest;
import rpl.fitbook.model.laporan.Laporan;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LaporanService {
    List<Laporan> getAll();

    Laporan create(LaporanRequest request);

    Laporan findById(Integer id);

    void deleteById(Integer id);

    Laporan update(Integer id, LaporanRequest request);
}
