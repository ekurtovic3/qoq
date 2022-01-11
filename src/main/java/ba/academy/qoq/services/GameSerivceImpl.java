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
import java.util.stream.Collectors;

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
        MapEntity mapEntity = generateMap(weightFacotr);

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

    private MapEntity generateMap(WeightFacotr weightFacotr) {
        MapEntity mapEntity = new MapEntity();
        if(weightFacotr.equals(WeightFacotr.EASY))
            mapEntity.setNumberOfDungeons(5);
        else if(weightFacotr.equals(WeightFacotr.MEDIUM))
            mapEntity.setNumberOfDungeons(10);
        else if(weightFacotr.equals(WeightFacotr.HARD))
            mapEntity.setNumberOfDungeons(15);
        return mapEntity;
    }

    @Override
    public GameDto move(int id) {
        GameEntitiy gameEntitiy = gameRepository.findBy(id);
        LevelEntity levelEntity = levelRepository.findBy(gameEntitiy.getLevel().getId());
        MapEntity mapEntity = mapRepository.findById(levelEntity.getMap().getId());
        DungeonEntitiy newDungeon=new DungeonEntitiy();
        Set<DungeonEntitiy>  dungeonsSS=mapEntity.getDungeons();
        DungeonEntitiy currentDungeon = mapEntity.getCurrentDungeon();
        List<DungeonEntitiy>  dungeons=dungeonsSS.stream().filter(dungeon->{return dungeon.getId()>currentDungeon.getId();
        }).collect(Collectors.toList());

        if(currentDungeon.getOrdinalNumber()==mapEntity.getNumberOfDungeons()) {
            newDungeon.setId(0);
            return gameDtoTransformer.toDto(gameEntitiy);
        }
        else  {
        int pomId=dungeons.get(0).getId();
        for(DungeonEntitiy dungeon:dungeons){
            if(dungeon.getId()<pomId)
                pomId=dungeon.getId();
        }
      //  newDungeon=dungeonRepository.findById(currentDungeon.getId() + 1);
        newDungeon=dungeonRepository.findById(pomId);
        mapEntity.setCurrentDungeon(newDungeon);
        mapRepository.persist(mapEntity);
        mapRepository.flush();
        }

        if (newDungeon.getMonster() == null && newDungeon.getItem() != null)
            collect(newDungeon, gameEntitiy);

        return gameDtoTransformer.toDto(gameEntitiy);
    }

    private void collect(DungeonEntitiy dungeonEntitiy, GameEntitiy gameEntitiy) {
        ItemEntitiy item = itemRepository.findById(dungeonEntitiy.getItem().getId());
        PlayerEntity player = playerRepository.findById(gameEntitiy.getPlayer().getId());
        if (item instanceof PowerUpEnttiy) {
            player.setDamage(player.getDamage() + ((PowerUpEnttiy) item).getDamage());
            dungeonEntitiy.setItem(null);
        } else if (item instanceof HealerEntity) {
            player.setHealingPoting(player.getHealingPoting() + ((HealerEntity) item).getHealth());
            dungeonEntitiy.setItem(null);

        }

    }

    @Override
    public GameDto fight(int id) {
        int monsterHealth = 0;
        GameEntitiy gameEntitiy = gameRepository.findBy(id);
        LevelEntity levelEntity = levelRepository.findBy(gameEntitiy.getLevel().getId());
        MapEntity mapEntity = mapRepository.findById(levelEntity.getMap().getId());
        final int currentDungeonId = mapEntity.getCurrentDungeon().getId();
        DungeonEntitiy dungeonEntitiy = dungeonRepository.findById(currentDungeonId);
        PlayerEntity playerEntity = playerRepository.findBy(gameEntitiy.getPlayer().getId());
        int playerHealth = playerEntity.getHealth();



        MonsterEntity monsterEntity = monsterRepository.findById(dungeonEntitiy.getMonster().getId());
        if (monsterEntity != null)
            monsterHealth = monsterEntity.getHealth();
        while (playerHealth > 0 && monsterHealth > 0) {
            monsterHealth = monsterHealth - playerEntity.getDamage() * (int) Math.floor(Math.random() * (6 - 0));//ThreadLocalRandom.current().nextInt(0, 6)/5;
            playerHealth = playerHealth - monsterEntity.getDamage() * (int) Math.floor(Math.random() * (6 - 0));//ThreadLocalRandom.current().nextInt(0, 6)/5;
        }
        if (playerHealth >= 0) {
            playerEntity.setHealth(playerHealth);
            collect(dungeonEntitiy, gameEntitiy);
            dungeonEntitiy.setMonster(null);
            //return 0;
        }
        else if (monsterHealth >= 0) playerEntity.setHealth(0);;
        playerRepository.persist(playerEntity);
       // gameRepository.persist(gameEntitiy);
        return gameDtoTransformer.toDto(gameEntitiy);
    }


    @Override
    public GameDto flee(int id) {
        GameEntitiy gameEntitiy = gameRepository.findBy(id);
        PlayerEntity playerEntity = playerRepository.findById(gameEntitiy.getPlayer().getId());
        LevelEntity levelEntity = levelRepository.findBy(gameEntitiy.getLevel().getId());
        MapEntity mapEntity = mapRepository.findById(levelEntity.getMap().getId());
        DungeonEntitiy newDungeon=new DungeonEntitiy();
        DungeonEntitiy currentDungeon = mapEntity.getCurrentDungeon();


        Set<DungeonEntitiy>  dungeonsSS=mapEntity.getDungeons();
        List<DungeonEntitiy>  dungeons=dungeonsSS.stream().filter(dungeon->{return dungeon.getId()>currentDungeon.getId();
        }).collect(Collectors.toList());

        if(currentDungeon.getOrdinalNumber()==mapEntity.getNumberOfDungeons()) {
            newDungeon.setId(0);
            return gameDtoTransformer.toDto(gameEntitiy);
        }
        else  {
            int pomId=dungeons.get(0).getId();
            for(DungeonEntitiy dungeon:dungeons){
                if(dungeon.getId()<pomId)
                    pomId=dungeon.getId();
            }
            //  newDungeon=dungeonRepository.findById(currentDungeon.getId() + 1);
            newDungeon=dungeonRepository.findById(pomId);
            mapEntity.setCurrentDungeon(newDungeon);
            mapRepository.persist(mapEntity);
            mapRepository.flush();
        }
        final int playerHeal = playerEntity.getHealth();
        playerEntity.setHealth(playerHeal - 10);
        if (playerHeal - 10 <= 0) return null;
        return gameDtoTransformer.toDto(gameEntitiy);



    }

    @Override
    public PlayerDto heal(int id) {
        GameEntitiy gameEntitiy = gameRepository.findBy(id);
        PlayerEntity playerEntity = playerRepository.findById(gameEntitiy.getPlayer().getId());
        int maxHeal = playerEntity.getHealth() + playerEntity.getHealingPoting();
        if (maxHeal <= 100) {
            playerEntity.setHealth(playerEntity.getHealth() + playerEntity.getHealingPoting());
            playerEntity.setHealingPoting(0);
        } else {
            playerEntity.setHealth(100);
            playerEntity.setHealingPoting(maxHeal - 100);

        }

        return playerDtoTransformer.toDto(playerEntity);
    }

    private void setQoq(Set<DungeonEntitiy> dungeons) {
        int size = dungeons.size();
        QoqEntity qoq = new QoqEntity();
        itemRepository.persist(qoq);
        int item = new Random().nextInt(size);
        int i = 0;
        for (DungeonEntitiy dungeonEntitiy : dungeons) {
            if (item == 0 && i == 0) item = 1;
           // if (i == item) dungeonEntitiy.setItem(itemRepository.findBy(qoq.getId()));
            if (dungeonEntitiy.getMonster()!=null) dungeonEntitiy.setItem(itemRepository.findBy(qoq.getId()));
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
            //if (i == size-1) dungeonEntitiy.setMonster(monsterRepository.findBy(monster.getId()));
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
        } else monsterEntity.setDamage(20);
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

    private DungeonEntitiy generateDungeon(MapEntity map, int i) {

        DungeonEntitiy dungeonEntitiy = new DungeonEntitiy();
        dungeonEntitiy.setMonster(null);
        ItemEntitiy item = generateItem();
        if (item != null && i != 0) dungeonEntitiy.setItem(item);
        dungeonEntitiy.setMap(map);
        dungeonEntitiy.setOrdinalNumber(i+1);
        dungeonRepository.persist(dungeonEntitiy);

        return dungeonEntitiy;
    }

    private Set<DungeonEntitiy> generateDungeons(WeightFacotr weightFacotr, MapEntity map) {
        Set<DungeonEntitiy> dungeonEntitiyList = new HashSet<>();
        if (weightFacotr.equals(WeightFacotr.EASY)) {
            for (int i = 0; i < 5; i++) {
               DungeonEntitiy dungeonEntitiy=generateDungeon(map, i);
               if(i==0) map.setCurrentDungeon(dungeonEntitiy);
                dungeonEntitiyList.add(dungeonEntitiy);

            }

        } else if (weightFacotr.equals(WeightFacotr.MEDIUM)) {
            for (int i = 0; i < 10; i++) {
                DungeonEntitiy dungeonEntitiy=generateDungeon(map, i);
                if(i==0) map.setCurrentDungeon(dungeonEntitiy);
                dungeonEntitiyList.add(dungeonEntitiy);
            }


        } else {
            for (int i = 0; i < 15; i++) {
                DungeonEntitiy dungeonEntitiy=generateDungeon(map, i);
                if(i==0) map.setCurrentDungeon(dungeonEntitiy);
                dungeonEntitiyList.add(dungeonEntitiy);
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
