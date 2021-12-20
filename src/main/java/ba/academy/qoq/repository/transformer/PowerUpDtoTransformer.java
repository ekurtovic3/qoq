package ba.academy.qoq.repository.transformer;

import ba.academy.qoq.dto.HelaerDto;
import ba.academy.qoq.dto.PowerUpDto;
import ba.academy.qoq.dto.QoqDto;
import ba.academy.qoq.repository.entities.PowerUpEnttiy;
import ba.academy.qoq.repository.entities.QoqEntity;

public class PowerUpDtoTransformer implements DtoTransformer<PowerUpEnttiy, PowerUpDto> {

    private DungeonDtoTransformer dungeonDtoTransformer=new DungeonDtoTransformer();

    @Override
    public PowerUpDto toDto(PowerUpEnttiy entity) {
       PowerUpDto powerUpDto= new PowerUpDto();
       powerUpDto.setId(entity.getId());
       powerUpDto.setDamage(entity.getDamage());
       powerUpDto.setDungeon(dungeonDtoTransformer.toDto(entity.getDungeon()));
        return powerUpDto;
    }

    @Override
    public PowerUpEnttiy toEntity(PowerUpDto dto, PowerUpEnttiy entityInstance) {
        entityInstance.setId(dto.getId());
        entityInstance.setDamage(dto.getDamage());
        return entityInstance;
    }
}
