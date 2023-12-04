package rpl.fitbook.model.pengguna;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "pengguna")
public class PenggunaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String name;

    @Column(name = "email", unique = true)
    private String email;
    
    private String password;
    
    private String displayName;
    
    private String noTelp;
  
}