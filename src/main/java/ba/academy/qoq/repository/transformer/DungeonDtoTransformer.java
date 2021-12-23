package ba.academy.qoq.repository.transformer;

import ba.academy.qoq.dto.*;
import ba.academy.qoq.repository.entities.DungeonEntitiy;
import ba.academy.qoq.repository.entities.HealerEntity;
import ba.academy.qoq.repository.entities.ItemEntitiy;
import ba.academy.qoq.repository.entities.PowerUpEnttiy;

import java.util.*;


public class DungeonDtoTransformer implements DtoTransformer<DungeonEntitiy, DungeonDto> {

    private ItemDtoTransformer itemDtoTransformer = new ItemDtoTransformer();
    private MonsterDtoTransformer monsterDtoTransformer = new MonsterDtoTransformer();

    @Override
    public DungeonDto toDto(DungeonEntitiy entity) {
        DungeonDto dungeonInput = new DungeonDto();
        dungeonInput.setId(entity.getId());

        if (entity.getMonster() != null) dungeonInput.setMonster(monsterDtoTransformer.toDto(entity.getMonster()));
        ItemEntitiy item = entity.getItem();

        if (item instanceof PowerUpEnttiy && item!=null) {
            PowerUpDto powerUpDto = new PowerUpDto();
            powerUpDto.setId(entity.getItem().getId());
            powerUpDto.setDamage(((PowerUpEnttiy) item).getDamage());
            dungeonInput.setItem(powerUpDto);
        } else if (item instanceof HealerEntity  && item!=null) {
            HelaerDto helaerDto = new HelaerDto();
            helaerDto.setId(entity.getItem().getId());
            helaerDto.setHealth(((HealerEntity) item).getHealth());
            dungeonInput.setItem(helaerDto);

        } else if(item!=null) {
            QoqDto qoqDto = new QoqDto();
            qoqDto.setId(entity.getItem().getId());
            dungeonInput.setItem(qoqDto);

        }
        return dungeonInput;
    }

    @Override
    public DungeonEntitiy toEntity(DungeonDto dto, DungeonEntitiy entityInstance) {
        entityInstance.setId(dto.getId());
        return entityInstance;
    }
}
