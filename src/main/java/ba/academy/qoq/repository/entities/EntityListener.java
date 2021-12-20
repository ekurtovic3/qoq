package ba.academy.qoq.repository.entities;

import java.time.LocalDateTime;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class EntityListener {

    @PrePersist
    public void prePersist(ba.academy.qoq.repository.entities.AbstractEntity entity) {
        entity.setCreatedOn(LocalDateTime.now());
        entity.setModifiedOn(LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate(ba.academy.qoq.repository.entities.AbstractEntity entity) {
        entity.setModifiedOn(LocalDateTime.now());
    }

}
