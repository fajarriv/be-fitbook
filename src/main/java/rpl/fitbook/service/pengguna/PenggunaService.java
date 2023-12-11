package rpl.fitbook.service.pengguna;


import rpl.fitbook.service.security.UserDetailsImpl;
import rpl.fitbook.model.pengguna.PenggunaModel;

public interface PenggunaService {

    public UserDetailsImpl getCurrentPengguna();
    public String getCurrentPenggunaId();
    public Boolean isPenggunaExist(String email);

    PenggunaModel updateProfile(String email, String newDisplayName, String newNoTelp);

}
