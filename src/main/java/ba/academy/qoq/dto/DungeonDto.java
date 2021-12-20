package ba.academy.qoq.dto;

import java.util.List;

public class DungeonDto {
    private Integer id;
    private MonsterDto monster;
    private List<ItemDto> items;


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

    public List<ItemDto> getItems() {
        return items;
    }

    public void setItems(List<ItemDto> items) {
        this.items = items;
    }


}
