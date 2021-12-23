package ba.academy.qoq.repository;

import ba.academy.qoq.repository.entities.DungeonEntitiy;
import ba.academy.qoq.repository.entities.GameEntitiy;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Transactional(Transactional.TxType.MANDATORY)
public class DungeonRepository extends Repository<DungeonEntitiy, Integer>{
    public DungeonRepository() {super(DungeonEntitiy.class);}

    public List<DungeonEntitiy> findAllAsList() {
        CriteriaQuery<DungeonEntitiy> query = criteriaQuery();
        Root<DungeonEntitiy> table = query.from(DungeonEntitiy.class);
        query.select(table);
        return entityManager()
                .createQuery(query).getResultList();
    }
}

