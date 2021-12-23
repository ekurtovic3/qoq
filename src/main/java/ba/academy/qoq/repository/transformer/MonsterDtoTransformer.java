package ba.academy.qoq.repository.transformer;

import ba.academy.qoq.dto.MonsterDto;
import ba.academy.qoq.repository.entities.MonsterEntity;

public class MonsterDtoTransformer implements DtoTransformer<MonsterEntity, MonsterDto> {

    @Override
    public MonsterDto toDto(MonsterEntity entity) {
        MonsterDto monsterDto=new MonsterDto();
        monsterDto.setId(entity.getId());
        monsterDto.setDamage(entity.getDamage());
        monsterDto.setHealth(entity.getHealth());
        return monsterDto;
    }

    @Override
    public MonsterEntity toEntity(MonsterDto dto, MonsterEntity entityInstance) {
        entityInstance.setDamage(dto.getDamage());
        entityInstance.setHealth(dto.getHealth());
        return entityInstance;
    }

}


