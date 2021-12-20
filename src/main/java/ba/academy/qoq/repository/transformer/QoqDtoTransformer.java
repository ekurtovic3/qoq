package ba.academy.qoq.repository.transformer;

import ba.academy.qoq.dto.HelaerDto;
import ba.academy.qoq.dto.QoqDto;
import ba.academy.qoq.repository.entities.QoqEntity;

public class QoqDtoTransformer implements DtoTransformer<QoqEntity, QoqDto> {

    private DungeonDtoTransformer dungeonDtoTransformer=new DungeonDtoTransformer();
    @Override
    public QoqDto toDto(QoqEntity entity) {
        QoqDto qoqDto=new QoqDto();
       qoqDto.setId(entity.getId());
       qoqDto.setDungeon(dungeonDtoTransformer.toDto(entity.getDungeon()));
       return qoqDto;

    }
    @Override
    public QoqEntity toEntity(QoqDto dto, QoqEntity entityInstance) {
        entityInstance.setId(dto.getId());
        return entityInstance;
    }
}
