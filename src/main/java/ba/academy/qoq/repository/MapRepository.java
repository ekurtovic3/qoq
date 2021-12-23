package ba.academy.qoq.repository;
import ba.academy.qoq.repository.entities.MapEntity;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;


@ApplicationScoped
@Transactional(Transactional.TxType.MANDATORY)
public class MapRepository extends Repository<MapEntity, Integer>{
    public MapRepository() {super(MapEntity.class);}
    public List<MapEntity> findAllAsList() {
        CriteriaQuery<MapEntity> query = criteriaQuery();
        Root<MapEntity> table = query.from(MapEntity.class);
        query.select(table);
        return entityManager()
                .createQuery(query).getResultList();
    }
}

