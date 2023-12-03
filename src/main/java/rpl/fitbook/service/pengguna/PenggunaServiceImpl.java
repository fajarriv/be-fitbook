package rpl.fitbook.service.pengguna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import rpl.fitbook.repository.pengguna.PenggunaRepository;
import rpl.fitbook.service.security.UserDetailsImpl;

@Service
@Transactional
public class PenggunaServiceImpl implements PenggunaService {

    @Autowired
    PenggunaRepository penggunaRepo;

    @Override
    public UserDetailsImpl getCurrentPengguna() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return userDetails;
    }

    @Override
    public Boolean isPenggunaExist(String email) {
        return penggunaRepo.findByEmail(email).isPresent();
    }

    @Override
    public String getCurrentPenggunaId() {
        return getCurrentPengguna().getId();
    }
}
