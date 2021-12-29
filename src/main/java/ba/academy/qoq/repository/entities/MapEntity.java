package ba.academy.qoq.repository.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

@Entity
@Table(schema = "qoq", name = "MAP")
public class MapEntity extends AbstractEntity {

    @SequenceGenerator(
            name = "mapSeq",
            sequenceName = "MAP_SEQ",
            schema = "qoq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mapSeq")

    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @OneToOne(mappedBy = "map", cascade = CascadeType.ALL)
    private LevelEntity level;

    @OneToOne
    @JoinColumn(name = "CURRENT_DUNGEON_ID")
    private DungeonEntitiy currentDungeon;

    @Column(name = "NUMBER_OF_DUNGEONS")
    private Integer numberOfDungeons;

    @OneToMany(mappedBy = "map")
    public Set<DungeonEntitiy> dungeons;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LevelEntity getLevel() {
        return level;
    }

    public void setLevel(LevelEntity level) {
        this.level = level;
    }

    public Set<DungeonEntitiy> getDungeons() {
        return dungeons;
    }

    public void setDungeons(Set<DungeonEntitiy> dungeons) {
        this.dungeons = dungeons;
    }

    public DungeonEntitiy getCurrentDungeon() {
        return currentDungeon;
    }

    public Integer getNumberOfDungeons() {
        return numberOfDungeons;
    }

    public void setNumberOfDungeons(Integer numberOfDungeons) {
        this.numberOfDungeons = numberOfDungeons;
    }

    public void setCurrentDungeon(DungeonEntitiy currentDungeon) {
        this.currentDungeon = currentDungeon;
    }
}
