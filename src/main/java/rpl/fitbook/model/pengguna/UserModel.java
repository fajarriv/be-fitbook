package rpl.fitbook.model.pengguna;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "user")
public class UserModel extends PenggunaModel {

}
