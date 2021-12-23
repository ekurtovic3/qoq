package ba.academy.qoq.repository.transformer;

import ba.academy.qoq.dto.*;
import ba.academy.qoq.repository.entities.DungeonEntitiy;
import ba.academy.qoq.repository.entities.HealerEntity;
import ba.academy.qoq.repository.entities.ItemEntitiy;
import ba.academy.qoq.repository.entities.PowerUpEnttiy;

import java.util.*;


public class DungeonDtoTransformer implements DtoTransformer<DungeonEntitiy, DungeonDto> {

    private ItemDtoTransformer itemDtoTransformer = new ItemDtoTransformer();
    private MonsterDtoTransformer monsterDtoTransformer= new MonsterDtoTransformer();

    @Override
    public DungeonDto toDto(DungeonEntitiy entity) {
        DungeonDto dungeonInput = new DungeonDto();
        dungeonInput.setId(entity.getId());
       if(entity.getMonster()!=null) dungeonInput.setMonster(monsterDtoTransformer.toDto(entity.getMonster()));
        final Set<ItemDto> items = new HashSet<>();
//      if(dungeonInput.getMonster()!=null) dungeonInput.setMonster(monsterDtoTransformer.toDto(entity.getMonster()));
//
//        for (ItemEntitiy itemEntity : entity.getItems()) {
//            if (itemEntity instanceof PowerUpEnttiy) {
//                PowerUpDto powerUpDto = new PowerUpDto();
//                powerUpDto.setDamage(((PowerUpEnttiy) itemEntity).getDamage());
//                items.add(powerUpDto);
//
//            } else if (itemEntity instanceof HealerEntity) {
//                HelaerDto helaerDto = new HelaerDto();
//                helaerDto.setHealth(((HealerEntity) itemEntity).getHealth());
//                items.add(helaerDto);
//            } else {
//                QoqDto qoqDto = new QoqDto();
//                items.add(qoqDto);
//            }
//            if(items!=null && !items.isEmpty())
//                dungeonInput.setItems(items);
//
//        }

        return dungeonInput;
    }
    @Override
    public DungeonEntitiy toEntity(DungeonDto dto, DungeonEntitiy entityInstance) {
        entityInstance.setId(dto.getId());
        return entityInstance;
    }
}
