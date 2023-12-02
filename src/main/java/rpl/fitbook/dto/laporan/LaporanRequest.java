package rpl.fitbook.dto.laporan;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LaporanRequest {
        @NotNull(message = "pelapor is mandatory")
        private String pelapor;

        @NotNull(message = "isiLaporan is mandatory")
        private String isiLaporan;

        private String balasanLaporan;
}
