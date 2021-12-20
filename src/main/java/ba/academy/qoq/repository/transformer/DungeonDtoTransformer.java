package ba.academy.qoq.repository.transformer;

import ba.academy.qoq.dto.DungeonDto;
import ba.academy.qoq.repository.entities.DungeonEntitiy;
import ba.academy.qoq.repository.entities.ItemEntitiy;
import java.util.List;


public class DungeonDtoTransformer implements DtoTransformer<DungeonEntitiy, DungeonDto> {

    private ItemDtoTransformer itemDtoTransformer = new ItemDtoTransformer();
    private MonsterDtoTransformer monsterDtoTransformer= new MonsterDtoTransformer();

    @Override
    public DungeonDto toDto(DungeonEntitiy entity) {
        DungeonDto dungeonInput = new DungeonDto();
        dungeonInput.setId(entity.getId());

        final List<ItemEntitiy> items = entity.getItems();
        dungeonInput.setItems(itemDtoTransformer.toDtoList(items));
        dungeonInput.setMonster(monsterDtoTransformer.toDto(entity.getMonster()));

        return dungeonInput;
    }

    @Override
    public DungeonEntitiy toEntity(DungeonDto dto, DungeonEntitiy entityInstance) {
        entityInstance.setId(dto.getId());
        return entityInstance;
    }
}
