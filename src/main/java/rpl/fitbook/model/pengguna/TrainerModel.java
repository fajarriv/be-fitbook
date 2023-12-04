package rpl.fitbook.model.pengguna;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "trainer")
public class TrainerModel extends PenggunaModel {
    private String bio;
    private Float rating;
}
