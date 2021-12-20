package ba.academy.qoq.repository.transformer;

import ba.academy.qoq.dto.DungeonDto;
import ba.academy.qoq.dto.MapDto;
import ba.academy.qoq.repository.entities.DungeonEntitiy;
import ba.academy.qoq.repository.entities.ItemEntitiy;
import ba.academy.qoq.repository.entities.MapEntity;

import java.util.List;

public class MapDtoTransformer implements DtoTransformer<MapEntity, MapDto> {

    private DungeonDtoTransformer dungeonDtoTransformer=new DungeonDtoTransformer();

    @Override
    public MapDto toDto(MapEntity entity) {
        MapDto mapInput = new MapDto();
        mapInput.setId(entity.getId());

        final List<DungeonEntitiy> dungeons = entity.getDungeons();
        mapInput.setDungeons(dungeonDtoTransformer.toDtoList(dungeons));

        return  mapInput;
    }

    @Override
    public MapEntity toEntity(MapDto dto, MapEntity entityInstance) {
        entityInstance.setId(dto.getId());
        return entityInstance;
    }
}
