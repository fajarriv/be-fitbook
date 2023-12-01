package rpl.fitbook.model.pengguna;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TrainerModel extends PenggunaModel {
    private String bio;
    private Float rating;
}
