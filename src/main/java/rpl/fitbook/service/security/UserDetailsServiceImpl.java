package rpl.fitbook.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rpl.fitbook.exception.NotFoundException;
import rpl.fitbook.model.pengguna.PenggunaModel;
import rpl.fitbook.repository.pengguna.PenggunaRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  PenggunaRepository penggunaRepo;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    PenggunaModel user = penggunaRepo.findByEmail(email)
        .orElseThrow(() -> new NotFoundException("User Not Found with email: " + email));

    return UserDetailsImpl.build(user);
  }
}