package ba.academy.qoq.repository.transformer;

import ba.academy.qoq.dto.GameDto;
import ba.academy.qoq.dto.HelaerDto;
import ba.academy.qoq.repository.entities.HealerEntity;

public class HealerDtoTransformer implements DtoTransformer<HealerEntity, HelaerDto>{

    private DungeonDtoTransformer dungeonDtoTransformer=new DungeonDtoTransformer();
    @Override
    public HelaerDto toDto(HealerEntity entity) {
        HelaerDto helaerDto=new HelaerDto();
        helaerDto.setId(entity.getId());
        helaerDto.setHealth(entity.getHealth());
        helaerDto.setDungeon(dungeonDtoTransformer.toDto(entity.getDungeon()));
        return helaerDto;
    }

    @Override
    public HealerEntity toEntity(HelaerDto dto, HealerEntity entityInstance) {
        entityInstance.setId(dto.getId());
        entityInstance.setHealth(dto.getHealth());
        return entityInstance;
    }
}
