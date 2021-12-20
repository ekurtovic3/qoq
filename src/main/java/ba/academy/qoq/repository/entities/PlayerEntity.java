package ba.academy.qoq.repository.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(schema = "qoq", name = "PLAYER")
public class PlayerEntity extends AbstractEntity {

    @SequenceGenerator(
            name = "playerSeq",
            sequenceName = "PLAYER_SEQ",
            schema = "qoq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "playerSeq")


    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;


    @Column(name = "HEALTH", nullable = false)
    private Integer health;


    @Column(name = "DAMAGE", nullable = false)
    private Integer damage;


    @Column(name = "HEALING_POTING", nullable = false)
    private Integer healingPoting;

    @OneToOne(mappedBy = "player", cascade = CascadeType.ALL)
    private GameEntitiy game;

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

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    public Integer getHealingPoting() {
        return healingPoting;
    }

    public void setHealingPoting(Integer healingPoting) {
        this.healingPoting = healingPoting;
    }
}
