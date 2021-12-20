package ba.academy.qoq.repository.entities;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "QOQ")
@DiscriminatorValue("1")
public class QoqEntity extends ItemEntitiy{

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
}
