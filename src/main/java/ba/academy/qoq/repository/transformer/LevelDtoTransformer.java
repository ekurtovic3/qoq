package ba.academy.qoq.repository.transformer;

import ba.academy.qoq.dto.LevelDto;
import ba.academy.qoq.repository.entities.LevelEntity;

public class LevelDtoTransformer implements DtoTransformer<LevelEntity, LevelDto>{
    private MapDtoTransformer mapDtoTransformer=new MapDtoTransformer();

    @Override
    public LevelDto toDto(LevelEntity entity) {
        LevelDto levelDto=new LevelDto();
        levelDto.setId(entity.getId());
        levelDto.setMapDto(mapDtoTransformer.toDto(entity.getMap()));
        levelDto.setWeightFactor(entity.getWeightFacotr());
        return levelDto;
    }

    @Override
    public LevelEntity toEntity(LevelDto dto, LevelEntity entityInstance) {
        entityInstance.setId(dto.getId());
        entityInstance.setWeightFacotr(dto.getWeightFactor());
        return entityInstance;
    }
}
