package ba.academy.qoq.dto;

public class PowerUpDto extends ItemDto{
    private Integer damage;

    @Override
    public Integer getId() {
        return super.getId();
    }

    @Override
    public void setId(Integer id) {
        super.setId(id);
    }

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
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
