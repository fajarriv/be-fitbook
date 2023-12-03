package rpl.fitbook.service.auth;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import rpl.fitbook.dto.auth.LoginPengguna;
import rpl.fitbook.dto.auth.RegisterPengguna;
import rpl.fitbook.exception.BadRequestException;
import rpl.fitbook.model.pengguna.AdminModel;
import rpl.fitbook.model.pengguna.PenggunaModel;
import rpl.fitbook.model.pengguna.Role;
import rpl.fitbook.model.pengguna.TrainerModel;
import rpl.fitbook.model.pengguna.UserModel;
import rpl.fitbook.repository.pengguna.AdminRepository;
import rpl.fitbook.repository.pengguna.PenggunaRepository;
import rpl.fitbook.repository.pengguna.TrainerRepository;
import rpl.fitbook.repository.pengguna.UserRepository;
import rpl.fitbook.service.pengguna.PenggunaService;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Autowired
    PenggunaService penggunaService;

    @Autowired
    PenggunaRepository penggunaRepo;

    @Autowired
    TrainerRepository trainerRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    AdminRepository adminRepo;

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String register(RegisterPengguna request) {

        if (penggunaService.isPenggunaExist(request.getEmail())) {
            throw new BadRequestException("Email already exist");
        }

        String roleReq = request.getRole();
        PenggunaModel createdPengguna;

        if (roleReq.equals("Trainer")) {
            createdPengguna = registerTrainer(request);
        } else if (roleReq.equals("User")) {
            createdPengguna = registerUser(request);
        } else if (roleReq.equals("Admin")) {
            createdPengguna = registerAdmin(request);
        } else {
            throw new BadRequestException("Invalid role");
        }

        String token = createToken(createdPengguna);
        return token;
    }

    @Override
    public String login(LoginPengguna request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    private PenggunaModel registerTrainer(RegisterPengguna reqTrainer) {
        if (reqTrainer.getBio() == null)
            throw new BadRequestException("Bio is Required for Trainer");

        TrainerModel trainer = new TrainerModel();
        BeanUtils.copyProperties(reqTrainer, trainer, "role", "password");
        trainer.setPassword(passwordEncoder.encode(reqTrainer.getPassword()));
        trainer.setRole(Role.Trainer);
        trainerRepo.save(trainer);
        return trainer;
    }

    private PenggunaModel registerUser(RegisterPengguna reqUser) {
        UserModel user = new UserModel();
        BeanUtils.copyProperties(reqUser, user, "role", "password");
        user.setPassword(passwordEncoder.encode(reqUser.getPassword()));
        user.setRole(Role.User);
        userRepo.save(user);
        return user;
    }

    private PenggunaModel registerAdmin(RegisterPengguna reqAdmin) {
        AdminModel admin = new AdminModel();
        BeanUtils.copyProperties(reqAdmin, admin, "role", "password");
        admin.setPassword(passwordEncoder.encode(reqAdmin.getPassword()));
        admin.setRole(Role.Admin);
        adminRepo.save(admin);
        return admin;
    }

    private String createToken(PenggunaModel pengguna) {
        return jwtService.generateToken(pengguna);
    }

}
