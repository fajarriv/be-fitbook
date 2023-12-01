package rpl.fitbook.service.laporan;

import rpl.fitbook.dto.laporan.LaporanRequest;
import rpl.fitbook.exception.laporan.LaporanDoesNotExistException;
import rpl.fitbook.model.laporan.Laporan;
import rpl.fitbook.repository.LaporanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LaporanServiceImpl implements LaporanService {
    private final LaporanRepository laporanRepository;

    @Override
    public List<Laporan> getAll() {
        return this.laporanRepository.findAll();
    }

    @Override
    public Laporan create(LaporanRequest request) {
        Laporan newLaporan = Laporan.builder()
                .pelapor(request.getPelapor())
                .isiLaporan(request.getIsiLaporan())
                .balasanLaporan("")
                .build();
        return laporanRepository.save(newLaporan);
    }

    @Override
    public void deleteById(Integer id) {
        if (isLaporanDoesNotExist(id)) {
            throw new LaporanDoesNotExistException(id);
        } else {
            laporanRepository.deleteById(id);
        }
    }

    @Override
    public Laporan findById(Integer id) {
        return this.laporanRepository.findById(id)
                .orElseThrow(() -> new LaporanDoesNotExistException(id));
    }

    @Override
    public Laporan update(Integer id, LaporanRequest request) {
        Laporan laporan = this.laporanRepository.findById(id)
                .orElseThrow(() -> new LaporanDoesNotExistException(id));

        laporan.setBalasanLaporan(request.getBalasanLaporan());
        return laporanRepository.save(laporan);
    }

    private boolean isLaporanDoesNotExist(Integer id) {
        return !this.laporanRepository.existsById(id);
    }
}
