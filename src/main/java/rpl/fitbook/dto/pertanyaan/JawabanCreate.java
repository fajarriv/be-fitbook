package rpl.fitbook.dto.pertanyaan;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JawabanCreate {
    @NotBlank(message = "jawaban is required.")
    private String jawaban;
}
