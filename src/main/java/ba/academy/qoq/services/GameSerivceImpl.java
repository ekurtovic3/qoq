package ba.academy.qoq.services;

import ba.academy.qoq.dto.*;
import ba.academy.qoq.repository.*;
import ba.academy.qoq.repository.entities.*;
import ba.academy.qoq.repository.transformer.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@ApplicationScoped
@Transactional
public class GameSerivceImpl implements GameSerivce {

    final private GameDtoTransformer gameDtoTransformer = new GameDtoTransformer();
    final private MonsterDtoTransformer monsterDtoTransformer = new MonsterDtoTransformer();
    final private DungeonDtoTransformer dungeonDtoTransformer = new DungeonDtoTransformer();
    final private PlayerDtoTransformer playerDtoTransformer = new PlayerDtoTransformer();
    final private LevelDtoTransformer levelDtoTransformer = new LevelDtoTransformer();
    final private MapDtoTransformer mapDtoTransformer = new MapDtoTransformer();

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


        PlayerEntity player = generatePlayer();
        MonsterEntity monsterEntity = generateMonster(weightFacotr);
        MapEntity mapEntity = new MapEntity();
        Set<DungeonEntitiy> dungeons = generateDungeons(weightFacotr, mapEntity);
        setMonster(dungeons, monsterEntity);
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

        return gameDto;
    }

    @Override
    public DungeonDto move(int id) {
        GameEntitiy gameEntitiy = gameRepository.findBy(id);
        LevelEntity levelEntity = levelRepository.findBy(gameEntitiy.getLevel().getId());
        MapEntity mapEntity = mapRepository.findById(levelEntity.getMap().getId());
        int currentDungeonId = mapEntity.getCurrentDungeon().getId();
        //TODO: KAD DODES DO KRAJA
        if (mapEntity.getCurrentDungeon() == null) return null;
        mapEntity.setCurrentDungeon(dungeonRepository.findById(currentDungeonId + 1));
        mapRepository.flush();
        return dungeonDtoTransformer.toDto(mapEntity.getCurrentDungeon());
    }

    @Override
    public int fight(int id)
    {
        int monsterHealth=0;
        GameEntitiy gameEntitiy = gameRepository.findBy(id);
        LevelEntity levelEntity = levelRepository.findBy(gameEntitiy.getLevel().getId());
        MapEntity mapEntity = mapRepository.findById(levelEntity.getMap().getId());
        final int currentDungeonId = mapEntity.getCurrentDungeon().getId();
        DungeonEntitiy dungeonEntitiy=dungeonRepository.findById(currentDungeonId);
        PlayerEntity playerEntity=playerRepository.findBy(gameEntitiy.getPlayer().getId());
        int playerHealth= playerEntity.getHealth();

        MonsterEntity monsterEntity= monsterRepository.findById(dungeonEntitiy.getMonster().getId());
        if(monsterEntity!=null)
        monsterHealth=monsterEntity.getHealth();
        while(playerHealth>0 && monsterHealth>0)
        {
            monsterHealth = monsterHealth-playerEntity.getDamage()* (int) Math.floor(Math.random()*(6-0));//ThreadLocalRandom.current().nextInt(0, 6)/5;
            playerHealth = playerHealth-monsterEntity.getDamage()*(int) Math.floor(Math.random()*(6-0));//ThreadLocalRandom.current().nextInt(0, 6)/5;

        }
        System.out.println("MONSTER HEALTH:"+monsterHealth);
        System.out.println("PLAYER HEALTH:"+playerHealth);

        if(monsterHealth>=0) return 1;
        if(playerHealth>=0 ) return 0;
        return 3;
    }

    @Override
    public DungeonDto flee(int id)
    {
        GameEntitiy gameEntitiy = gameRepository.findBy(id);
        PlayerEntity playerEntity=playerRepository.findBy(gameEntitiy.getPlayer().getId());
        LevelEntity levelEntity = levelRepository.findBy(gameEntitiy.getLevel().getId());
        MapEntity mapEntity = mapRepository.findById(levelEntity.getMap().getId());
        int currentDungeonId = mapEntity.getCurrentDungeon().getId();
        //TODO: KAD DODES DO KRAJA
        if (mapEntity.getCurrentDungeon() == null) return null;
        if(mapEntity.getCurrentDungeon().getMonster()==null) return null;
        mapEntity.setCurrentDungeon(dungeonRepository.findById(currentDungeonId + 1));
        mapRepository.flush();
        final int playerHeal= playerEntity.getHealth();
        playerEntity.setHealth(playerHeal-10);
        return dungeonDtoTransformer.toDto(mapEntity.getCurrentDungeon());

    }
    private void setQoq(Set<DungeonEntitiy> dungeons) {
        int size = dungeons.size();
        QoqEntity qoq = new QoqEntity();
        itemRepository.persist(qoq);
        int item = new Random().nextInt(size);
        int i = 0;
        for (DungeonEntitiy dungeonEntitiy : dungeons) {
            if (item == 0 && i == 0) item = size;
            if (i == item) dungeonEntitiy.setItem(itemRepository.findBy(qoq.getId()));
            i++;
        }
    }
    private void setMonster(Set<DungeonEntitiy> dungeons, MonsterEntity monster) {
        int size = dungeons.size();
        int item = new Random().nextInt(size);
        int i = 0;
        for (DungeonEntitiy dungeonEntitiy : dungeons) {
            if (item == 0 && i == 0)
                item = 1;
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

    private ItemEntitiy generateItem() {
        Random rand = new Random();
        double itemRand = rand.nextDouble();
        if (itemRand <= 0.1) {
            PowerUpEnttiy powerUpEnttiy = new PowerUpEnttiy();
            powerUpEnttiy.setDamage(10);
            itemRepository.persist(powerUpEnttiy);
            return powerUpEnttiy;
        } else if (itemRand <= 0.2) {
            HealerEntity healerEntity = new HealerEntity();
            healerEntity.setHealth(20);
            itemRepository.persist(healerEntity);
            return healerEntity;
        }
        return null;
    }

    private DungeonEntitiy generateDungeon(MapEntity map) {

        DungeonEntitiy dungeonEntitiy = new DungeonEntitiy();
        dungeonEntitiy.setMonster(null);
        ItemEntitiy item = generateItem();
        if (item != null) dungeonEntitiy.setItem(item);
        dungeonEntitiy.setMap(map);
        dungeonRepository.persist(dungeonEntitiy);

        return dungeonEntitiy;
    }

    private Set<DungeonEntitiy> generateDungeons(WeightFacotr weightFacotr, MapEntity map) {
        Set<DungeonEntitiy> dungeonEntitiyList = new HashSet<>();
        if (weightFacotr.equals(WeightFacotr.EASY)) {
            for (int i = 0; i < 5; i++) {
                dungeonEntitiyList.add(generateDungeon(map));

            }

        } else if (weightFacotr.equals(WeightFacotr.EASY)) {
            for (int i = 0; i < 10; i++) {
                dungeonEntitiyList.add(generateDungeon(map));

            }


        } else {
            for (int i = 0; i < 15; i++) {
                dungeonEntitiyList.add(generateDungeon(map));

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
