package ba.academy.qoq.dto;

import java.util.List;

public class MapDto {
    private Integer id;
    private List<DungeonDto> dungeons;
    private DungeonDto currentDungeon;
    private Integer numberOfDungeons;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<DungeonDto> getDungeons() {
        return dungeons;
    }

    public void setDungeons(List<DungeonDto> dungeons) {
        this.dungeons = dungeons;
    }

    public DungeonDto getCurrentDungeon() {
        return currentDungeon;
    }

    public Integer getNumberOfDungeons() {
        return numberOfDungeons;
    }

    public void setNumberOfDungeons(Integer numberOfDungeons) {
        this.numberOfDungeons = numberOfDungeons;
    }

    public void setCurrentDungeon(DungeonDto currentDungeon) {
        this.currentDungeon = currentDungeon;
    }
}
