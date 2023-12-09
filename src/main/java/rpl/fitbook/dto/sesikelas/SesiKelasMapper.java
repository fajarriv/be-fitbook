package rpl.fitbook.dto.sesikelas;

import org.springframework.beans.BeanUtils;

import rpl.fitbook.dto.pengguna.TrainerMapper;
import rpl.fitbook.model.sesikelas.SesiKelasModel;

public class SesiKelasMapper {
    
    public static SesiKelasDto toDto (SesiKelasModel model){
        SesiKelasDto dto = new SesiKelasDto();

        BeanUtils.copyProperties(model, dto);
        dto.setTrainer(TrainerMapper.toDto(model.getTrainer()));

        return dto;
    }
}
