package rpl.fitbook.controller;

import rpl.fitbook.controller.laporan.LaporanController;
import rpl.fitbook.dto.laporan.LaporanRequest;
import rpl.fitbook.exception.NotFoundException;
import rpl.fitbook.model.laporan.Laporan;
import rpl.fitbook.service.laporan.LaporanServiceImpl;
import rpl.fitbook.service.auth.JwtService;
import rpl.fitbook.service.security.UserDetailsServiceImpl;
import rpl.fitbook.repository.LaporanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import rpl.fitbook.Util;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LaporanController.class)
@AutoConfigureMockMvc(addFilters = false)
public class LaporanControllerTest {
    private static final String END_POINT_PATH = "/api";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @MockBean
    private LaporanServiceImpl laporanService;

    Laporan laporan;

    Laporan laporanUpdate;

    LaporanRequest laporanRequest;

    @MockBean
    private LaporanRepository laporanRepository;

    @BeforeEach
    void setUp() {
        laporan = Laporan.builder()
                .id(1)
                .pelapor("pelapor")
                .isiLaporan("isi laporan")
                .balasanLaporan("")
                .build();

        laporanRequest = LaporanRequest.builder()
                .pelapor("Azka")
                .isiLaporan("Lapor 123")
                .balasanLaporan("WWWWW")
                .build();
    }

    @Test
    void shouldReturnAllLaporan() throws Exception {
        String requestURI = END_POINT_PATH + "/laporan";

        Laporan laporan1 = Laporan.builder()
                .id(2)
                .pelapor("pelapor1")
                .isiLaporan("isi laporan1")
                .balasanLaporan("")
                .build();

        List<Laporan> laporanList = List.of(laporan, laporan1);

        when(laporanService.getAll()).thenReturn(laporanList);

        mockMvc.perform(get(requestURI))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.responseObject[0].pelapor").value(laporanList.get(0).getPelapor()))
                .andExpect(jsonPath("$.responseObject[0].isiLaporan").value(laporanList.get(0).getIsiLaporan()))
                .andExpect(jsonPath("$.responseObject[0].balasanLaporan").value(laporanList.get(0).getBalasanLaporan()))
                .andExpect(jsonPath("$.responseObject[1].pelapor").value(laporanList.get(1).getPelapor()))
                .andExpect(jsonPath("$.responseObject[1].isiLaporan").value(laporanList.get(1).getIsiLaporan()))
                .andExpect(jsonPath("$.responseObject[1].balasanLaporan").value(laporanList.get(1).getBalasanLaporan()))
                .andDo(print());

        verify(laporanService, times(1)).getAll();
    }

    @Test
    void shouldReturnLaporanById() throws Exception {
        String requestURI = END_POINT_PATH + "/laporan/1";

        when(laporanService.findById(1)).thenReturn(laporan);

        mockMvc.perform(get(requestURI))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.responseObject.pelapor").value(laporan.getPelapor()))
                .andExpect(jsonPath("$.responseObject.isiLaporan").value(laporan.getIsiLaporan()))
                .andExpect(jsonPath("$.responseObject.balasanLaporan").value(laporan.getBalasanLaporan()))
                .andDo(print());

        verify(laporanService, times(1)).findById(1);
    }

    @Test
    void shouldReturnLaporanDoesNotExistException() throws Exception {
        String requestURI = END_POINT_PATH + "/laporan/3";

        when(laporanService.findById(3)).thenThrow(new NotFoundException("Laporan with id 3 does not exist"));

        mockMvc.perform(get(requestURI))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Laporan with id 3 does not exist"))
                .andExpect(jsonPath("$.status").value("NOT_FOUND"))
                .andDo(print());

        verify(laporanService, times(1)).findById(3);
    }

    @Test
    void shouldCreateLaporan() throws Exception {
        String requestURI = END_POINT_PATH + "/buatLaporan";

        when(laporanService.create(laporanRequest)).thenReturn(laporan);

        String requestBody = Util.mapToJson(laporanRequest);

        mockMvc.perform(post(requestURI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.responseObject.pelapor").value(laporan.getPelapor()))
                .andExpect(jsonPath("$.responseObject.isiLaporan").value(laporan.getIsiLaporan()))
                .andExpect(jsonPath("$.responseObject.balasanLaporan").value(laporan.getBalasanLaporan()))
                .andDo(print());

        verify(laporanService, times(1)).create(laporanRequest);
    }

    @Test
    void shouldDeleteLaporanById() throws Exception {
        String requestURI = END_POINT_PATH + "/hapusLaporan/1";

        doNothing().when(laporanService).deleteById(1);

        mockMvc.perform(delete(requestURI))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(jsonPath("$.status").value("OK"))
                .andDo(print());

        verify(laporanService, times(1)).deleteById(1);
    }

    @Test
    void shouldUpdateBalasanLaporan() throws Exception {
        String requestURI = END_POINT_PATH + "/balasLaporan/1";

        laporan.setBalasanLaporan("Balasan laporan");
        LaporanRequest laporanRequest = LaporanRequest.builder()
            .balasanLaporan("Balasan laporan")
            .build();
        
        when(laporanService.update(any(Integer.class), any(LaporanRequest.class))).thenReturn(laporan);

        String requestBody = Util.mapToJson(laporanRequest);

        mockMvc.perform(put(requestURI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(jsonPath("$.status").value("OK"))
                .andDo(print());
    }
}
