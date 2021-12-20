package ba.academy.qoq.repository.entities;

import ba.academy.qoq.dto.WeightFacotr;

import javax.persistence.*;

@Entity
@Table(schema = "qoq", name = "LEVEL")
public class LevelEntity extends AbstractEntity{

    @SequenceGenerator(
            name = "levelSeq",
            sequenceName = "LEVEL_SEQ",
            schema = "qoq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "levelSeq")

    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name="WEIGHT_FACOTR")
    private WeightFacotr weightFacotr;

    @OneToOne(mappedBy = "level", cascade = CascadeType.ALL)
    private GameEntitiy game;

    @OneToOne
    @JoinColumn(name = "MAP_ID")
    private MapEntity map;


    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GameEntitiy getGame() {
        return game;
    }

    public void setGame(GameEntitiy game) {
        this.game = game;
    }

    public MapEntity getMap() {
        return map;
    }

    public void setMap(MapEntity map) {
        this.map = map;
    }

    public WeightFacotr getWeightFacotr() {
        return weightFacotr;
    }

    public void setWeightFacotr(WeightFacotr weightFacotr) {
        this.weightFacotr = weightFacotr;
    }
}
