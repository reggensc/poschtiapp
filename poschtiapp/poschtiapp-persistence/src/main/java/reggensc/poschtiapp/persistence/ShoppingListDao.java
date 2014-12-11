package reggensc.poschtiapp.persistence;

import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import reggensc.poschtiapp.domain.ShoppingList;

@Repository
public class ShoppingListDao extends AbstractDao<ShoppingList> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingListDao.class);

    public ShoppingListDao() {
        super(ShoppingList.class);
    }

    public List<ShoppingList> getByOwnerEmail(String email) {
        LOGGER.trace("Trying to load all entities of type {}", entityClass.getCanonicalName());

        Query query = em.createNamedQuery("findByOwnerEmail");
        query.setParameter("email", email);
        @SuppressWarnings("unchecked")
        List<ShoppingList> resultList = query.getResultList();

        LOGGER.trace("Successfully loaded {} entities of type {}", resultList.size(), entityClass.getCanonicalName());
        return resultList;
    }

}
