package ba.academy.qoq.dto;

import java.util.List;

public class MapDto {
    private Integer id;
    private List<DungeonDto> dungeons;

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
}
