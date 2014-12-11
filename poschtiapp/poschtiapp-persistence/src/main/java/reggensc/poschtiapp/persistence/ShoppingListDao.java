package reggensc.poschtiapp.persistence;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import reggensc.poschtiapp.domain.ShoppingList;
import reggensc.poschtiapp.domain.User;

@Repository
public class ShoppingListDao extends AbstractDao<ShoppingList> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingListDao.class);

    public ShoppingListDao() {
        super(ShoppingList.class);
    }

    public List<ShoppingList> getByOwnerEmail(String email) {
        LOGGER.trace("Trying to load all entities of type {}", entityClass.getCanonicalName());

        // CriteriaQuery<ShoppingList> cq = em.getCriteriaBuilder().createQuery(ShoppingList.class);
        //
        // Root<ShoppingList> shoppingLists = cq.from(ShoppingList.class);
        // Join<ShoppingList, User> owners = shoppingLists.join("owners");
        // Predicate isOwner = em.getCriteriaBuilder().equal(owners.get("email"), email);
        // cq.select(shoppingLists).where(isOwner);
        //
        // List<ShoppingList> resultList = em.createQuery(cq).getResultList();

        Query query = em.createNamedQuery("findByOwnerEmail");
        query.setParameter("email", email);
        List<ShoppingList> resultList = query.getResultList();

        LOGGER.trace("Successfully loaded {} entities of type {}", resultList.size(), entityClass.getCanonicalName());
        return resultList;
    }

}
