package ba.academy.qoq.repository.entities;
import javax.persistence.*;

import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(schema = "qoq", name = "DUNGEON")
public class DungeonEntitiy extends AbstractEntity{

    @SequenceGenerator(
            name = "dungeonSeq",
            sequenceName = "DUNGEON_SEQ",
            schema = "qoq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dungeonSeq")

    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "MONSTER_ID")
    private MonsterEntity monster;

    @OneToOne(mappedBy = "dungeon")
    public ItemEntitiy item;

    @OneToOne(mappedBy = "currentDungeon", cascade = CascadeType.ALL)
    private MapEntity mapEntity;

    @ManyToOne
    @JoinColumn(name="MAP_ID")
    public MapEntity map;

    @Override
    public Integer getId() {
        return id;
    }

    public ItemEntitiy getItem() {
        return item;
    }

    public void setItem(ItemEntitiy item) {
        this.item = item;
    }

    public MapEntity getMap() {
        return map;
    }

    public void setMap(MapEntity map) {
        this.map = map;
    }

    public MonsterEntity getMonster() {
        return monster;
    }

    public void setMonster(MonsterEntity monster) {
        this.monster = monster;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
