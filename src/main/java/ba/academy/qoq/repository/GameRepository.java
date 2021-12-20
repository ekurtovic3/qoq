package ba.academy.qoq.repository;


import ba.academy.qoq.repository.entities.GameEntitiy;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Transactional(Transactional.TxType.MANDATORY)
public class GameRepository extends Repository<GameEntitiy, Integer>{
    public GameRepository() {super(GameEntitiy.class);}

    public List<GameEntitiy> findAllAsList() {
        CriteriaQuery<GameEntitiy> query = criteriaQuery();
        Root<GameEntitiy> table = query.from(GameEntitiy.class);
        query.select(table);
        return entityManager()
                .createQuery(query).getResultList();
    }
}
