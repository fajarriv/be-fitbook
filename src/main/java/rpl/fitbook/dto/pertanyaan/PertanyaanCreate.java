package rpl.fitbook.dto.pertanyaan;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PertanyaanCreate {
    @NotBlank(message = "pertanyaan is required.")
    private String pertanyaan;
    @NotBlank(message = "sesiKelasId is required.")
    private String sesiKelasId;
}
