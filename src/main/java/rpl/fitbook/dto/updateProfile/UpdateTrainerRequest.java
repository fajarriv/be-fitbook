package rpl.fitbook.dto.updateProfile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTrainerRequest {

    private String newDisplayName;
    private String newNoTelp;
}
