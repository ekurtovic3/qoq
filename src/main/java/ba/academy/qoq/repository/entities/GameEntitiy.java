package ba.academy.qoq.repository.entities;

import ba.academy.qoq.dto.PlayerDto;

import javax.persistence.*;

@Entity
@Table(schema = "qoq", name = "GAME")
public class GameEntitiy  extends AbstractEntity {

    @SequenceGenerator(
            name = "gameSeq",
            sequenceName = "GAME_SEQ",
            schema = "qoq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gameSeq")

    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "LEVEL_ID")
    private LevelEntity level;

    @Override
    public Integer getId() {
        return id;
    }

    @OneToOne
    @JoinColumn(name = "PLAYER_ID")
    private PlayerEntity player;

    public void setId(Integer id) {
        this.id = id;
    }

    public LevelEntity getLevel() {
        return level;
    }

    public void setLevel(LevelEntity level) {
        this.level = level;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public void setPlayer(PlayerEntity player) {
        this.player = player;
    }
}
