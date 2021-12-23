package ba.academy.qoq.dto;

import java.util.List;
import java.util.Set;

public class DungeonDto {
    private Integer id;
    private MonsterDto monster;
    private ItemDto item;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MonsterDto getMonster() {
        return monster;
    }

    public void setMonster(MonsterDto monster) {
        this.monster = monster;
    }

    public ItemDto getItem() {
        return item;
    }

    public void setItem(ItemDto item) {
        this.item = item;
    }
}
