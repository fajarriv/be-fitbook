package rpl.fitbook.controller.laporan;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rpl.fitbook.dto.laporan.LaporanRequest;
import rpl.fitbook.model.laporan.Laporan;
import rpl.fitbook.service.laporan.LaporanService;
import rpl.fitbook.util.Response;

import java.util.List;

public class LaporanController {
    private final LaporanService laporanService;

    public LaporanController(LaporanService laporanService) {
        this.laporanService = laporanService;
    }

    @GetMapping("/laporan")
    public ResponseEntity<Object> getAllLaporan() {
        List<Laporan> laporanList = this.laporanService.getAll();
        return new ResponseEntity<>(new Response("Success", HttpStatus.OK, "OK", laporanList), HttpStatus.OK);
    }

    @PostMapping("/buatLaporan")
    public ResponseEntity<Object> createLaporan(@RequestBody LaporanRequest request) {
        Laporan laporan = this.laporanService.create(request);
        return new ResponseEntity<>(new Response("Success", HttpStatus.OK, "OK", laporan), HttpStatus.OK);
    }

    @GetMapping("/laporan/{laporanId}")
    public ResponseEntity<Object> getLaporanById(@PathVariable Integer laporanId) {
        Laporan laporan = this.laporanService.findById(laporanId);
        return new ResponseEntity<>(new Response("Success", HttpStatus.OK, "OK", laporan), HttpStatus.OK);
    }

    @DeleteMapping("/laporan/{laporanId}")
    public ResponseEntity<Object> deleteLaporanById(@PathVariable Integer laporanId) {
        this.laporanService.deleteById(laporanId);
        return new ResponseEntity<>(new Response("Success", HttpStatus.OK, "OK", null), HttpStatus.OK);
    }

    @PutMapping("/laporan/{laporanId}")
    public ResponseEntity<Object> updateLaporanById(@PathVariable Integer laporanId, @RequestBody LaporanRequest request) {
        Laporan laporan = this.laporanService.update(laporanId, request);
        return new ResponseEntity<>(new Response("Success", HttpStatus.OK, "OK", laporan), HttpStatus.OK);
    }
}
