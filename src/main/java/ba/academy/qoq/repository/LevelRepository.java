package ba.academy.qoq.repository;

import ba.academy.qoq.repository.entities.DungeonEntitiy;
import ba.academy.qoq.repository.entities.LevelEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Transactional(Transactional.TxType.MANDATORY)
public class LevelRepository extends Repository<LevelEntity, Integer>{
    public LevelRepository() {super(LevelEntity.class);}

    public List<LevelEntity> findAllAsList() {
        CriteriaQuery<LevelEntity> query = criteriaQuery();
        Root<LevelEntity> table = query.from(LevelEntity.class);
        query.select(table);
        return entityManager()
                .createQuery(query).getResultList();
    }
}

