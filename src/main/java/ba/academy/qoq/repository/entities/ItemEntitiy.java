package ba.academy.qoq.repository.entities;

import javax.persistence.*;

@Entity
@Table(schema = "qoq", name = "ITEM")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class ItemEntitiy  extends AbstractEntity {
    @SequenceGenerator(
            name = "itemeq",
            sequenceName = "ITEM_SEQ",
            schema = "qoq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemSeq")


    @Id
    @Column(name = "ID")
    private Integer id;

    @OneToOne
    @JoinColumn(name="DUNGEON_ID")
    public DungeonEntitiy dungeon;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DungeonEntitiy getDungeon() {
        return dungeon;
    }

    public void setDungeon(DungeonEntitiy dungeon) {
        this.dungeon = dungeon;
    }

    @Override
    public String toString() {
        return "ItemEntitiy{" +
                "id=" + id +
                ", dungeon=" +
                '}';
    }
}
