package ba.academy.qoq.repository.entities;


import javax.persistence.*;

@Entity
@Table(name = "HEALER")
@DiscriminatorValue("2")
public class HealerEntity extends ItemEntitiy{

    @Column(name = "HEALTH")
    private Integer health;

    @Override
    public Integer getId() {
        return super.getId();
    }

    @Override
    public void setId(Integer id) {
        super.setId(id);
    }

    @Override
    public DungeonEntitiy getDungeon() {
        return super.getDungeon();
    }

    @Override
    public void setDungeon(DungeonEntitiy dungeon) {
        super.setDungeon(dungeon);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }
}
