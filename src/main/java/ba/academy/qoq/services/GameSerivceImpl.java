package ba.academy.qoq.services;

import ba.academy.qoq.dto.*;
import ba.academy.qoq.repository.*;
import ba.academy.qoq.repository.entities.*;
import ba.academy.qoq.repository.transformer.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;

@ApplicationScoped
@Transactional
public class GameSerivceImpl implements GameSerivce {

    final private GameDtoTransformer gameDtoTransformer = new GameDtoTransformer();
    final private MonsterDtoTransformer monsterDtoTransformer = new MonsterDtoTransformer();
    final private DungeonDtoTransformer dungeonDtoTransformer = new DungeonDtoTransformer();
    final private PlayerDtoTransformer playerDtoTransformer = new PlayerDtoTransformer();
    final private LevelDtoTransformer levelDtoTransformer = new LevelDtoTransformer();
    final private MapDtoTransformer mapDtoTransformer= new MapDtoTransformer();

    @Inject
    GameRepository gameRepository;
    @Inject
    MonsterRepository monsterRepository;
    @Inject
    ItemRepository itemRepository;
    @Inject
    DungeonRepository dungeonRepository;
    @Inject
    LevelRepository levelRepository;
    @Inject
    MapRepository mapRepository;
    @Inject
    PlayerRepository playerRepository;

    @Override
    public GameDto createGame(WeightFacotr weightFacotr) {

        Random rand = new Random();
        double double_random=rand.nextDouble();
        PlayerEntity player = generatePlayer();
        MonsterEntity monsterEntity = generateMonster(weightFacotr);
        MapEntity mapEntity = new MapEntity();
        Set<DungeonEntitiy> dungeons = generateDungeons(weightFacotr, mapEntity);
        setMonster(dungeons,monsterEntity);
        setQoq(dungeons);

        mapEntity.setDungeons(dungeons);

        mapRepository.persist(mapEntity);

        //Level
        LevelEntity level = generateLevel(weightFacotr, mapEntity);

        //Game
        GameEntitiy gameEntitiy = new GameEntitiy();
        gameEntitiy.setLevel(level);
        gameEntitiy.setPlayer(player);
        gameRepository.persist(gameEntitiy);

        GameDto gameDto = gameDtoTransformer.toDto(gameEntitiy);

        //TODO: Item
        PowerUpEnttiy itemEntitiy = new PowerUpEnttiy();
        itemEntitiy.setDamage(1);
        itemRepository.persist(itemEntitiy);

        return gameDto;
    }

    @Override
    public DungeonDto move(int id){
        GameEntitiy gameEntitiy=gameRepository.findBy(id);
        LevelEntity levelEntity=levelRepository.findBy(gameEntitiy.getLevel().getId());
        MapEntity mapEntity=mapRepository.findById(levelEntity.getMap().getId());
        int currentDungeonId= mapEntity.getCurrentDungeon().getId();

        if(mapEntity.getCurrentDungeon().getId()==null) return null;
        mapEntity.setCurrentDungeon(dungeonRepository.findById(currentDungeonId+1));
        mapRepository.flush();
        return   dungeonDtoTransformer.toDto(mapEntity.getCurrentDungeon()) ;
    }

    private void setQoq(Set<DungeonEntitiy> dungeons) {
        int size = dungeons.size();
        QoqEntity qoq= new QoqEntity();
        int item = new Random().nextInt(size);
        int i = 0;
        for(DungeonEntitiy dungeonEntitiy : dungeons)
        {
            //TODO: UBACIT QOQ
           // if (i == item) dungeonEntitiy.setMonster(monsterRepository.findBy(monster.getId()));
            i++;
        }
    }

    private void setMonster(Set<DungeonEntitiy> dungeons,MonsterEntity monster) {
        int size = dungeons.size();
        int item = new Random().nextInt(size);
        int i = 0;
        for(DungeonEntitiy dungeonEntitiy : dungeons)
        {
            if (i == item) dungeonEntitiy.setMonster(monsterRepository.findBy(monster.getId()));
            i++;
        }
    }

    private MonsterEntity generateMonster(WeightFacotr weightFacotr) {
        MonsterEntity monsterEntity = new MonsterEntity();
        monsterEntity.setHealth(100);
        if (weightFacotr.equals(WeightFacotr.EASY)) {
            monsterEntity.setDamage(5);
        } else if (weightFacotr.equals(WeightFacotr.MEDIUM)) {
            monsterEntity.setDamage(10);
        } else monsterEntity.setDamage(15);
        monsterRepository.persist(monsterEntity);
        return monsterEntity;
    }

    private Set<DungeonEntitiy> generateDungeons(WeightFacotr weightFacotr, MapEntity map) {
        Set<DungeonEntitiy> dungeonEntitiyList = new HashSet<>();
        if (weightFacotr.equals(WeightFacotr.EASY)) {
            for (int i = 0; i < 5; i++) {
                DungeonEntitiy dungeonEntitiy = new DungeonEntitiy();
                dungeonEntitiy.setMonster(null);
                dungeonEntitiyList.add(dungeonEntitiy);
                dungeonEntitiy.setMap(map);
                dungeonRepository.persist(dungeonEntitiy);

            }

        } else if (weightFacotr.equals(WeightFacotr.EASY)) {
            for (int i = 0; i < 10; i++) {
                DungeonEntitiy dungeonEntitiy = new DungeonEntitiy();
                dungeonEntitiy.setMonster(null);
                dungeonEntitiyList.add(dungeonEntitiy);
                dungeonEntitiy.setMap(map);
                dungeonRepository.persist(dungeonEntitiy);

            }


        } else {
            for (int i = 0; i < 15; i++) {
                DungeonEntitiy dungeonEntitiy = new DungeonEntitiy();
                dungeonEntitiy.setMonster(null);
                dungeonEntitiyList.add(dungeonEntitiy);
                dungeonEntitiy.setMap(map);
                dungeonRepository.persist(dungeonEntitiy);

            }
        }
        map.setCurrentDungeon(dungeonEntitiyList.stream().findFirst().get());
        return dungeonEntitiyList;
    }

    private PlayerEntity generatePlayer() {
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setDamage(10);
        playerEntity.setHealth(100);
        playerEntity.setHealingPoting(0);
        playerRepository.persist(playerEntity);
        return playerEntity;
    }

    private LevelEntity generateLevel(WeightFacotr weightFacotr, MapEntity mapEntity) {
        LevelEntity levelEntity = new LevelEntity();
        levelEntity.setWeightFacotr(weightFacotr);
        levelEntity.setMap(mapEntity);
        levelRepository.persist(levelEntity);
        return levelEntity;
    }
}
