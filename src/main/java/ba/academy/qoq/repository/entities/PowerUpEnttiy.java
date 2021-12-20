package ba.academy.qoq.repository.entities;

import javax.persistence.*;

@Entity
@Table(name = "POWERUP")
@DiscriminatorValue("3")
public class PowerUpEnttiy extends ItemEntitiy {

    @Column(name = "DAMAGE")
    private Integer damage;

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

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }
}
