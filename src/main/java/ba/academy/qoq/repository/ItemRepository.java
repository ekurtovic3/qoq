package ba.academy.qoq.repository;

import ba.academy.qoq.repository.entities.GameEntitiy;
import ba.academy.qoq.repository.entities.ItemEntitiy;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Transactional(Transactional.TxType.MANDATORY)
public class ItemRepository extends Repository<ItemEntitiy, Integer>{
    public ItemRepository() {super(ItemEntitiy.class);}

    public List<ItemEntitiy> findAllAsList() {
        CriteriaQuery<ItemEntitiy> query = criteriaQuery();
        Root<ItemEntitiy> table = query.from(ItemEntitiy.class);
        query.select(table);
        return entityManager()
                .createQuery(query).getResultList();
    }
}