package ba.academy.qoq.dto;

public class ItemDto {
    private Integer id;
    private DungeonDto dungeon;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DungeonDto getDungeon() {
        return dungeon;
    }

    public void setDungeon(DungeonDto dungeon) {
        this.dungeon = dungeon;
    }
}
