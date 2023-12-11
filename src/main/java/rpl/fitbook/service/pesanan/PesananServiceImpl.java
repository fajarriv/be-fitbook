package rpl.fitbook.service.pesanan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import rpl.fitbook.exception.BadRequestException;
import rpl.fitbook.model.peasanan.PesananModel;
import rpl.fitbook.model.peasanan.PesananStatus;
import rpl.fitbook.model.pengguna.UserModel;
import rpl.fitbook.repository.pesanan.PesananRepository;
import rpl.fitbook.service.pengguna.PenggunaService;
import rpl.fitbook.service.sesikelas.SesiKelasService;
import rpl.fitbook.service.user.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PesananServiceImpl implements PesananService {

    @Autowired
    PesananRepository pesananRepo;

    @Autowired
    PenggunaService penggunaService;

    @Autowired
    UserService userService;

    @Autowired
    SesiKelasService sesiKelasService;

    @Override
    public PesananModel createPesanan(String idKelas) {
        UserModel currentUser = userService.getUserById(penggunaService.getCurrentPenggunaId());

        //Check if user already ordered this class or not
        if (Boolean.TRUE.equals(isAlreadyOrdered(idKelas, currentUser))) {
            throw new BadRequestException("Anda sudah mendaftar kelas ini");
        }

        sesiKelasService.incrementParticipant(idKelas);
        PesananModel newPesanan = new PesananModel();
        newPesanan.setSesiKelas(sesiKelasService.getSesiKelasById(idKelas));
        newPesanan.setUser(currentUser);
        newPesanan.setStatus(PesananStatus.Ongoing);

        return pesananRepo.save(newPesanan);
    }

    @Override
    public List<PesananModel> getAllPesananUserByStatus(String status) {
        UserModel currentUser = userService.getUserById(penggunaService.getCurrentPenggunaId());
        return pesananRepo.findAllByUserAndStatus(currentUser, PesananStatus.valueOf(status));
    }

    private Boolean isAlreadyOrdered(String idKelas, UserModel currentUser) {
        // Get all idKelas from daftarpesanan currentUser
        List<String> daftarIdKelas = new ArrayList<>();
        currentUser.getDaftarPesanan().forEach(pesanan -> {
            daftarIdKelas.add(pesanan.getSesiKelas().getId());
        });
        return daftarIdKelas.contains(idKelas);
    }
}
