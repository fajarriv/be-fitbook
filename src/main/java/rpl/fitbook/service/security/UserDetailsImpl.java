package rpl.fitbook.service.security;

import rpl.fitbook.model.pengguna.PenggunaModel;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;

// Wrapper of UserDetails, so we can extract other information from the user
@Getter
public class UserDetailsImpl implements UserDetails {

    private String id;
    private String email;
    private String role;
    private String displayName;

    @JsonIgnore
    private String password;

    public UserDetailsImpl(String id, String email, String name, String displayName, String password) {
    }

    public static UserDetailsImpl build(PenggunaModel pengguna) {
        return new UserDetailsImpl(
                pengguna.getId(),
                pengguna.getEmail(),
                pengguna.getRole().name(),
                pengguna.getDisplayName(),
                pengguna.getPassword()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var authority = new SimpleGrantedAuthority(role);
        return Collections.singletonList(authority);
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}