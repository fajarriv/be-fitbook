package rpl.fitbook.model.pengguna;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
// import lombok.AllArgsConstructor;
// import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "admin")
public class AdminModel extends PenggunaModel {

}
