package rpl.fitbook.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import rpl.fitbook.model.laporan.Laporan;
import rpl.fitbook.repository.LaporanRepository;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LaporanRepositoryTest {
    @Autowired
    private LaporanRepository laporanRepository;

    Laporan laporan;

    @BeforeEach
    void setUp() {
        laporan = Laporan.builder()
                .pelapor("Azka")
                .isiLaporan("Laporan")
                .balasanLaporan("")
                .build();
        laporanRepository.save(laporan);
    }

    @AfterEach
    void tearDown() {
        laporanRepository.deleteAll();
    }

    @Test
    void testFindAll() {
        List<Laporan> laporanList = laporanRepository.findAll();

        Assertions.assertNotNull(laporanList);
    }


    @Test
    void testFindByIdOrFindByPelapor() {
        Optional<Laporan> optionalLaporan = laporanRepository.findById(laporan.getId());
        Optional<Laporan> optionalLaporan2 = laporanRepository.findByPelapor("Azka");

        Assertions.assertTrue(optionalLaporan.isPresent());
        Assertions.assertTrue(optionalLaporan2.isPresent());
    }

    @Test
    void testFindByIdOrFindByPelaporNotFound() {
        Optional<Laporan> optionalLaporan = laporanRepository.findById(100);
        Optional<Laporan> optionalLaporan2 = laporanRepository.findByPelapor("Fajar");

        Assertions.assertFalse(optionalLaporan.isPresent());
        Assertions.assertFalse(optionalLaporan2.isPresent());
    }

    @Test
    void testDeleteByIdOrDeleteByPelapor() {
        laporanRepository.deleteById(laporan.getId());
        laporanRepository.deleteByPelapor("Azka");

        Optional<Laporan> optionalLaporan = laporanRepository.findById(laporan.getId());
        Optional<Laporan> optionalLaporan2 = laporanRepository.findByPelapor("Azka");

        Assertions.assertFalse(optionalLaporan.isPresent());
        Assertions.assertFalse(optionalLaporan2.isPresent());
    }

    @Test
    void testDeleteByIdOrDeleteByPelaporNotFound() {
        laporanRepository.deleteById(100);
        laporanRepository.deleteByPelapor("Fajar");

        Optional<Laporan> optionalLaporan = laporanRepository.findById(laporan.getId());
        Optional<Laporan> optionalLaporan2 = laporanRepository.findByPelapor("Azka");

        Assertions.assertTrue(optionalLaporan.isPresent());
        Assertions.assertTrue(optionalLaporan2.isPresent());
    }
}
