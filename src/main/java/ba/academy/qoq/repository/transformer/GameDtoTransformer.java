package ba.academy.qoq.repository.transformer;

import ba.academy.qoq.dto.GameDto;
import ba.academy.qoq.repository.entities.GameEntitiy;

public class GameDtoTransformer implements DtoTransformer<GameEntitiy, GameDto>{

    private LevelDtoTransformer levelDtoTransformer=new LevelDtoTransformer();
    private PlayerDtoTransformer playerDtoTransformer=new PlayerDtoTransformer();

    @Override
    public GameDto toDto(GameEntitiy entity) {
        GameDto gameDto=new GameDto();
        gameDto.setId(entity.getId());
        gameDto.setLevelDto(levelDtoTransformer.toDto(entity.getLevel()));
        gameDto.setPlayerDto(playerDtoTransformer.toDto(entity.getPlayer()));
        return gameDto;
    }

    @Override
    public GameEntitiy toEntity(GameDto dto, GameEntitiy entityInstance) {
        entityInstance.setId(dto.getId());
        return entityInstance;
    }
}
