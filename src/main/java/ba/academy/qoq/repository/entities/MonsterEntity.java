package ba.academy.qoq.repository.entities;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(schema = "qoq", name = "MONSTER")
public class MonsterEntity  extends AbstractEntity {
    @SequenceGenerator(
            name = "monsterSeq",
            sequenceName = "MONSTER_SEQ",
            schema = "qoq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "monsterSeq")

    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;


    @Column(name = "DAMAGE")
    private Integer damage;

    @Column(name = "HEALTH")
    private Integer health;

    @OneToOne(mappedBy = "monster", cascade = CascadeType.ALL)
    private DungeonEntitiy dungeon;

    @Override
    public Integer getId() {
        return id;
    }

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }
}
