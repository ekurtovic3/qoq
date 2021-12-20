package ba.academy.qoq.dto;

public class HelaerDto extends ItemDto{
    private Integer health;

    @Override
    public void setId(Integer id) {
        super.setId(id);
    }

    @Override
    public Integer getId() {
        return super.getId();
    }

    public Integer getHealth() {
        return health;
    }
    public void setHealth(Integer health) {
        this.health = health;
    }

    @Override
    public DungeonDto getDungeon() {
        return super.getDungeon();
    }

    @Override
    public void setDungeon(DungeonDto dungeon) {
        super.setDungeon(dungeon);
    }
}
