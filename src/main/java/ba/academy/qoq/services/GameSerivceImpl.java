package ba.academy.qoq.services;

import ba.academy.qoq.dto.GameDto;
import ba.academy.qoq.dto.MonsterDto;
import ba.academy.qoq.repository.GameRepository;
import ba.academy.qoq.repository.ItemRepository;
import ba.academy.qoq.repository.MonsterRepository;
import ba.academy.qoq.repository.entities.*;
import ba.academy.qoq.repository.transformer.DungeonDtoTransformer;
import ba.academy.qoq.repository.transformer.GameDtoTransformer;
import ba.academy.qoq.repository.transformer.MonsterDtoTransformer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class GameSerivceImpl implements GameSerivce{

   final private GameDtoTransformer gameDtoTransformer = new GameDtoTransformer();
   final private MonsterDtoTransformer monsterDtoTransformer = new MonsterDtoTransformer();
   final private DungeonDtoTransformer dungeonDtoTransformer= new DungeonDtoTransformer();


    @Inject  GameRepository gameRepository;
    @Inject    MonsterRepository monsterRepository;
    @Inject   ItemRepository itemRepository;
    @Override
    public GameDto createGame() {
//        MonsterDto monsterDto=new MonsterDto();
//        monsterDto.setDamage(50);
//        final MonsterEntity monsterEntity = monsterDtoTransformer.toEntity(monsterDto, new MonsterEntity());
//        monsterRepository.persist(monsterEntity);
//
        GameEntitiy gameEntitiy= new GameEntitiy();
        MapEntity mapEntity=new MapEntity();
        PowerUpEnttiy itemEntitiy=new PowerUpEnttiy();
       itemEntitiy.setDamage(1);
        itemRepository.persist(itemEntitiy);
        return null;

    }
}
