package ba.academy.qoq.repository;
import ba.academy.qoq.repository.entities.PlayerEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Transactional(Transactional.TxType.MANDATORY)
public class PlayerRepository extends Repository<PlayerEntity, Integer>{
    public PlayerRepository() {super(PlayerEntity.class);}

    public List<PlayerEntity> findAllAsList() {
        CriteriaQuery<PlayerEntity> query = criteriaQuery();
        Root<PlayerEntity> table = query.from(PlayerEntity.class);
        query.select(table);
        return entityManager()
                .createQuery(query).getResultList();
    }
}

