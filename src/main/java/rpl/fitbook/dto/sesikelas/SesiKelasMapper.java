package rpl.fitbook.dto.sesikelas;

import org.springframework.beans.BeanUtils;

import rpl.fitbook.dto.pengguna.TrainerMapper;
import rpl.fitbook.model.sesikelas.SesiKelasModel;

import java.util.ArrayList;
import java.util.List;

public class SesiKelasMapper {
    
    public static SesiKelasDto toDto (SesiKelasModel model){
        SesiKelasDto dto = new SesiKelasDto();

        BeanUtils.copyProperties(model, dto);
        dto.setTrainer(TrainerMapper.toDto(model.getTrainer()));

        return dto;
    }

    public static List<SesiKelasMini> toHomePageDto (List<SesiKelasModel> allSesiKelas){
        List<SesiKelasMini> result = new ArrayList<>();

        for (SesiKelasModel sesiKelas : allSesiKelas) {
            SesiKelasMini mini = new SesiKelasMini();
            BeanUtils.copyProperties(sesiKelas, mini);
            result.add(mini);
        }


        return result;
    }
}
