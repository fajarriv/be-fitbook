package rpl.fitbook.service;

import rpl.fitbook.dto.laporan.LaporanRequest;
import rpl.fitbook.exception.laporan.LaporanDoesNotExistException;
import rpl.fitbook.model.laporan.Laporan;
import rpl.fitbook.repository.LaporanRepository;
import rpl.fitbook.service.laporan.LaporanServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LaporanServiceTest {
    
    @InjectMocks
    private LaporanServiceImpl laporanService;

    @Mock
    private LaporanRepository laporanRepository;

    Laporan laporan;

    private final Integer id = 1;
    private final String pelapor = "Azka";
    private final String isiLaporan = "Laporan";

    @Test
    void whenGetAllLaporanShouldReturnListOfLaporan() {
        Laporan newLaporan = Laporan.builder()
                .id(id)
                .pelapor(pelapor)
                .isiLaporan(isiLaporan)
                .balasanLaporan("")
                .build();
        
        List<Laporan> laporanList = List.of(newLaporan);

        when(laporanRepository.findAll()).thenReturn(laporanList);
        List<Laporan> actual = laporanService.getAll();
        verify(laporanRepository, times(1)).findAll();
        Assertions.assertEquals(laporanList, actual);
    }

    @Test
    void whenFindByIdAndFoundShouldReturnLaporan() {
        Laporan newLaporan = Laporan.builder()
                .id(id)
                .pelapor(pelapor)
                .isiLaporan(isiLaporan)
                .balasanLaporan("")
                .build();

        when(laporanRepository.findById(id)).thenReturn(Optional.of(newLaporan));
        Laporan actual = laporanService.findById(id);
        verify(laporanRepository, times(1)).findById(id);
        Assertions.assertEquals(newLaporan, actual);
    }

    @Test
    void whenFindByIdAndNotFoundShouldThrowLaporanDoesNotExistException() {
        when(laporanRepository.findById(id)).thenReturn(Optional.empty());
        Assertions.assertThrows(LaporanDoesNotExistException.class, () -> laporanService.findById(id));
    }

    @Test
    void whenCreateLaporanShouldReturnLaporan() {
        LaporanRequest request = LaporanRequest.builder()
                .pelapor(pelapor)
                .isiLaporan(isiLaporan)
                .build();

        laporan = Laporan.builder()
                .pelapor(pelapor)
                .isiLaporan(isiLaporan)
                .balasanLaporan("")
                .build();

        when(laporanRepository.save(any(Laporan.class))).thenReturn(laporan);
        Laporan actual = laporanService.create(request);
        Assertions.assertEquals(laporan, actual);

    }

    @Test
    void whenDeleteByIdAndFoundShouldDeleteLaporan() {
        laporan = Laporan.builder()
                .id(id)
                .pelapor(pelapor)
                .isiLaporan(isiLaporan)
                .balasanLaporan("")
                .build();

        when(laporanRepository.existsById(id)).thenReturn(true);

        laporanService.deleteById(id);

        verify(laporanRepository, times(1)).deleteById(id);
    }

    @Test
    void whenDeleteByIdAndNotFoundShouldThrowLaporanDoesNotExistException() {
        when(laporanRepository.existsById(id)).thenReturn(false);
        Assertions.assertThrows(LaporanDoesNotExistException.class, () -> laporanService.deleteById(id));
    }

    @Test
    void whenUpdateLaporanAndFoundShouldReturnUpdatedLaporan() {
        LaporanRequest request = LaporanRequest.builder()
                .pelapor(pelapor)
                .isiLaporan(isiLaporan)
                .balasanLaporan("Balasan")
                .build();

        laporan = Laporan.builder()
                .id(id)
                .pelapor(pelapor)
                .isiLaporan(isiLaporan)
                .balasanLaporan("")
                .build();

        Laporan updatedLaporan = Laporan.builder()
                .id(id)
                .pelapor(pelapor)
                .isiLaporan(isiLaporan)
                .balasanLaporan("Balasan")
                .build();

        when(laporanRepository.findById(id)).thenReturn(Optional.of(laporan));
        when(laporanRepository.save(any(Laporan.class))).thenReturn(updatedLaporan);

        Laporan actual = laporanService.update(id, request);
        Assertions.assertEquals(updatedLaporan, actual);
    }

    @Test
    void whenUpdateLaporanAndNotFoundShouldThrowLaporanDoesNotExistException() {
        LaporanRequest request = LaporanRequest.builder()
                .pelapor(pelapor)
                .isiLaporan(isiLaporan)
                .balasanLaporan("Balasan")
                .build();

        when(laporanRepository.findById(id)).thenReturn(Optional.empty());
        Assertions.assertThrows(LaporanDoesNotExistException.class, () -> laporanService.update(id, request));
    }
}
