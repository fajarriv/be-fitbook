package rpl.fitbook.controller;

import rpl.fitbook.dto.laporan.LaporanRequest;
import rpl.fitbook.exception.laporan.LaporanDoesNotExistException;
import rpl.fitbook.model.laporan.Laporan;
import rpl.fitbook.service.laporan.LaporanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import rpl.fitbook.util.Response;
import rpl.fitbook.controller.laporan.LaporanController;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LaporanController.class)
@AutoConfigureMockMvc
public class LaporanControllerTest {
    private final String baseUrl = "/api/v1";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LaporanService laporanService;

    Laporan laporan;

    LaporanRequest laporanRequest;

    @BeforeEach
    void setUp() {
        laporan = Laporan.builder()
                .id(1)
                .pelapor("Azka")
                .isiLaporan("Laporan")
                .balasanLaporan("")
                .build();

        laporanRequest = LaporanRequest.builder()
                .build();
    }

    @Test
    void testGetLaporanShouldRetunr200OK () throws Exception {
        String requestURI = baseUrl + "/laporan";

        Laporan lapor = Laporan.builder()
                .id(1)
                .pelapor("Azka")
                .isiLaporan("Laporan")
                .balasanLaporan("")
                .build();

        List<Laporan> laporanList = List.of(lapor);

        when(laporanService.getAll()).thenReturn(laporanList);

        mockMvc.perform(get(requestURI))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.data[0].id").value(lapor.getId()))
                .andExpect(jsonPath("$.data[0].pelapor").value(lapor.getPelapor()))
                .andExpect(jsonPath("$.data[0].isiLaporan").value(lapor.getIsiLaporan()))
                .andExpect(jsonPath("$.data[0].balasanLaporan").value(lapor.getBalasanLaporan()))
                .andDo(print());
    }
}
