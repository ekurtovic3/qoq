package ba.academy.qoq.repository.transformer;

import ba.academy.qoq.dto.PlayerDto;
import ba.academy.qoq.repository.entities.PlayerEntity;

public class PlayerDtoTransformer implements DtoTransformer<PlayerEntity, PlayerDto>{

    @Override
    public PlayerDto toDto(PlayerEntity entity) {
        PlayerDto playerDto=new PlayerDto();
        playerDto.setId(entity.getId());
        playerDto.setDamage(entity.getDamage());
        playerDto.setHealingPoting(entity.getHealingPoting());
        playerDto.setHealth(entity.getHealth());

        return playerDto;
    }

    @Override
    public PlayerEntity toEntity(PlayerDto dto, PlayerEntity entityInstance) {
        entityInstance.setId(dto.getId());
        return entityInstance;
    }
}
