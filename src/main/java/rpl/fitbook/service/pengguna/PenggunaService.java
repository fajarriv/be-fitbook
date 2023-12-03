package rpl.fitbook.service.pengguna;


import rpl.fitbook.service.security.UserDetailsImpl;

public interface PenggunaService {

    public UserDetailsImpl getCurrentPengguna();
    public String getCurrentPenggunaId();
    public Boolean isPenggunaExist(String email);

}
