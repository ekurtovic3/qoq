package ba.academy.qoq.dto;

public class PlayerDto {
    private Integer id;
    private Integer health;
    private Integer damage;
    private Integer healingPoting;

    public Integer getId() {
        return id;
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
