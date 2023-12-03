package rpl.fitbook.service.laporan;

import rpl.fitbook.dto.laporan.LaporanRequest;
import rpl.fitbook.exception.NotFoundException;
import rpl.fitbook.model.laporan.Laporan;
import rpl.fitbook.repository.LaporanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
            throw new NotFoundException("Laporan with id " + id + " does not exist");
        } else {
            laporanRepository.deleteById(id);
        }
    }

    @Override
    public Laporan findById(Integer id) {
        return this.laporanRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Laporan with id " + id + " does not exist"));
    }

    @Override
    public Laporan update(Integer id, LaporanRequest request) {
        if (isLaporanDoesNotExist(id)) {
            throw new NotFoundException("Laporan with id " + id + " does not exist");
        } else {
            Laporan laporan = this.laporanRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Laporan with id " + id + " does not exist"));
            System.out.println(request.getBalasanLaporan());
            laporan.setBalasanLaporan(request.getBalasanLaporan());
            return this.laporanRepository.save(laporan);
        }
    }

    private boolean isLaporanDoesNotExist(Integer id) {
        return !this.laporanRepository.existsById(id);
    }
}
