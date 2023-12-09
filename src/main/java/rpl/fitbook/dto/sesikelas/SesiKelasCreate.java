package rpl.fitbook.dto.sesikelas;

import lombok.Builder;
import lombok.Data;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Pattern;

@Data
@Builder
public class SesiKelasCreate {

    @NotBlank(message = "judul is required.")
    private String judul;

    @NotBlank(message = "deskripsi is required.")
    private String deskripsi;

    @NotBlank(message = "jadwalWaktu is required.")
    @Pattern(regexp = "^([0-2][0-9]|3[0-1])-(0[1-9]|1[0-2])-(\\d{4}) ([0-1][0-9]|2[0-3]):([0-5][0-9])$", message = "Invalid jadwal format")
    private String jadwalWaktu;

    @NotBlank(message = "lokasi is required.")
    private String lokasi;

    @Min(value = 1, message = "minParticipant must be greater than 0.")
    private Integer maxParticipant;

}
