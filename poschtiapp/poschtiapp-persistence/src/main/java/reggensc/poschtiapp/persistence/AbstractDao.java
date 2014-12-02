package reggensc.poschtiapp.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;

import reggensc.poschtiapp.domain.AbstractEntity;

public abstract class AbstractDao<T extends AbstractEntity> {

	@PersistenceContext
	private EntityManager em;

	protected final Class<? extends AbstractEntity> entityClass;

	protected AbstractDao(Class<? extends AbstractEntity> entityClass) {
		this.entityClass = entityClass;
	}

	public T getById(Long id) {
		@SuppressWarnings("unchecked")
		T result = (T) em.find(entityClass, id);
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		CriteriaQuery<AbstractEntity> cq = em.getCriteriaBuilder().createQuery(
				AbstractEntity.class);
		cq.select(cq.from(entityClass));
		return (List<T>) em.createQuery(cq).getResultList();
	}

	public T saveOrUpdate(T entity) {
		T result = em.merge(entity);
		return result;
	}

	public void delete(T entity) {
		em.remove(entity);
	}
}
