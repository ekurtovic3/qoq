package ba.academy.qoq.services;

import ba.academy.qoq.dto.GameDto;
import ba.academy.qoq.dto.MonsterDto;
import ba.academy.qoq.repository.GameRepository;
import ba.academy.qoq.repository.MonsterRepository;
import ba.academy.qoq.repository.entities.MonsterEntity;
import ba.academy.qoq.repository.transformer.GameDtoTransformer;
import ba.academy.qoq.repository.transformer.MonsterDtoTransformer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class GameSerivceImpl implements GameSerivce{

    private GameDtoTransformer gameDtoTransformer = new GameDtoTransformer();
    private MonsterDtoTransformer monsterDtoTransformer = new MonsterDtoTransformer();

    @Inject  GameRepository gameRepository;
    @Inject    MonsterRepository monsterRepository;

    @Override
    public GameDto createGame() {
        MonsterDto monsterDto=new MonsterDto();
        monsterDto.setDamage(50);
        final MonsterEntity monsterEntity = monsterDtoTransformer.toEntity(monsterDto, new MonsterEntity());
        monsterRepository.persist(monsterEntity);
        return null;
    }
}
