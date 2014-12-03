package reggensc.poschtiapp.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reggensc.poschtiapp.domain.AbstractEntity;

public abstract class AbstractDao<T extends AbstractEntity> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractDao.class);

    @PersistenceContext
    private EntityManager em;

    protected final Class<? extends AbstractEntity> entityClass;

    protected AbstractDao(Class<? extends AbstractEntity> entityClass) {
        this.entityClass = entityClass;
    }

    public T getById(Long id) {
        LOGGER.trace("Trying to load entity with id={}", id);

        @SuppressWarnings("unchecked")
        T result = (T) em.find(entityClass, id);

        if (result != null) {
            LOGGER.trace("Successfully loaded entity with id={}", id);
        } else {
            LOGGER.trace("No entity with id={} could be loaded", id);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        LOGGER.trace("Trying to load all entities of type {}", entityClass.getCanonicalName());

        CriteriaQuery<AbstractEntity> cq = em.getCriteriaBuilder().createQuery(AbstractEntity.class);
        cq.select(cq.from(entityClass));
        List<T> resultList = (List<T>) em.createQuery(cq).getResultList();

        LOGGER.trace("Successfully loaded {} entities of type {}", resultList.size(), entityClass.getCanonicalName());
        return resultList;
    }

    public T saveOrUpdate(T entity) {
        LOGGER.trace("Trying to save or update entity: {}", entity);
        T result = em.merge(entity);
        LOGGER.trace("Successfully saved or updated entity: {}", result);
        return result;
    }

    public void delete(T entity) {
        LOGGER.trace("Trying to delete entity: {}", entity);
        em.remove(entity);
        LOGGER.trace("Successfully deleted entity: {}", entity);
    }
}
