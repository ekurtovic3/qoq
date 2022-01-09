package ba.academy.qoq.repository.transformer;

import ba.academy.qoq.dto.DungeonDto;
import ba.academy.qoq.dto.MapDto;
import ba.academy.qoq.repository.entities.DungeonEntitiy;
import ba.academy.qoq.repository.entities.ItemEntitiy;
import ba.academy.qoq.repository.entities.MapEntity;

import java.util.List;
import java.util.Set;

public class MapDtoTransformer implements DtoTransformer<MapEntity, MapDto> {

    private DungeonDtoTransformer dungeonDtoTransformer=new DungeonDtoTransformer();

    @Override
    public MapDto toDto(MapEntity entity) {
        MapDto mapInput = new MapDto();
        mapInput.setId(entity.getId());
        final Set<DungeonEntitiy> dungeons = entity.getDungeons();
        mapInput.setDungeons(dungeonDtoTransformer.toDtoList(dungeons));
       if(mapInput.getCurrentDungeon()!=null) mapInput.setCurrentDungeon(dungeonDtoTransformer.toDto(entity.getCurrentDungeon()));
        return  mapInput;
    }
    @Override
    public MapEntity toEntity(MapDto dto, MapEntity entityInstance) {
        entityInstance.setId(dto.getId());
        return entityInstance;
    }
}
