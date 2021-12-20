package ba.academy.qoq.repository;

import ba.academy.qoq.repository.entities.GameEntitiy;
import ba.academy.qoq.repository.entities.MonsterEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Transactional(Transactional.TxType.MANDATORY)
public class MonsterRepository extends Repository<MonsterEntity, Integer>{
    public MonsterRepository() {super(MonsterEntity.class);}

    public List<MonsterEntity> findAllAsList() {
        CriteriaQuery<MonsterEntity> query = criteriaQuery();
        Root<MonsterEntity> table = query.from(MonsterEntity.class);
        query.select(table);
        return entityManager()
                .createQuery(query).getResultList();
    }
}
