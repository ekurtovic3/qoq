package ba.academy.qoq.dto;

import java.util.List;
import java.util.Set;

public class DungeonDto {
    private Integer id;
    private MonsterDto monster;
    private Set<ItemDto> items;


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

    public Set<ItemDto> getItems() {
        return items;
    }

    public void setItems(Set<ItemDto> items) {
        this.items = items;
    }


}
