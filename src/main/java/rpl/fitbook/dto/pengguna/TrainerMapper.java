package rpl.fitbook.dto.pengguna;

import rpl.fitbook.model.pengguna.TrainerModel;

public class TrainerMapper {
    
    public static TrainerDto toDto(TrainerModel trainerModel) {
        TrainerDto trainerDto = new TrainerDto();
        trainerDto.setId(trainerModel.getId());
        trainerDto.setRole(trainerModel.getRole());
        trainerDto.setName(trainerModel.getName());
        trainerDto.setEmail(trainerModel.getEmail());
        trainerDto.setDisplayName(trainerModel.getDisplayName());
        trainerDto.setNoTelp(trainerModel.getNoTelp());
        trainerDto.setBio(trainerModel.getBio());
        trainerDto.setRating(trainerModel.getRating());
        return trainerDto;
    }
}
