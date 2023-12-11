package rpl.fitbook.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import rpl.fitbook.exception.NotFoundException;
import rpl.fitbook.model.peasanan.PesananModel;
import rpl.fitbook.model.pengguna.UserModel;
import rpl.fitbook.repository.pengguna.UserRepository;
import rpl.fitbook.service.pengguna.PenggunaService;
import rpl.fitbook.service.pesanan.PesananService;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepo;

    @Override
    public UserModel getUserById(String id) {
        UserModel trainer = userRepo.findById(id).orElseThrow(
                () -> new NotFoundException("Trainer dengan id " + id + " tidak ditemukan"));
        return trainer;
    }

}
