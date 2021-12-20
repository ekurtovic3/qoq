package ba.academy.qoq.repository.entities;

import javax.persistence.*;
import java.util.List;
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

    @OneToMany(mappedBy = "map")
    public List<DungeonEntitiy> dungeons;

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

    public List<DungeonEntitiy> getDungeons() {
        return dungeons;
    }

    public void setDungeons(List<DungeonEntitiy> dungeons) {
        this.dungeons = dungeons;
    }
}
