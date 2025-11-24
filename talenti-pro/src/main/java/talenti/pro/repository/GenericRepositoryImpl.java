package talenti.pro.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnitUtil;
import jakarta.persistence.Query;

public abstract class GenericRepositoryImpl<T extends Serializable> implements GenericRepository<T> {

    @PersistenceContext
    protected EntityManager em;

    private final Class<T> clazz;

    public GenericRepositoryImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

	@Override
	public T salvar(T entidade) {
		if (getEntityId(entidade) != null) {
			return em.merge(entidade);
		} else {
			em.persist(entidade);
			return entidade;
		}
	}

    @Override
    public void excluir(Long id) {
        T entidade = em.find(clazz, id);
        if (entidade != null) {
            em.remove(entidade);
        }
    }

    @Override
    public T buscarPorId(Long id) {
        return em.find(clazz, id);
    }
    
    @Override
    public Optional<T> buscarPorIdOpt(Long id) {
        return Optional.ofNullable(em.find(clazz, id));
    }


	@SuppressWarnings("unchecked")
	@Override
    public List<T> listarPaginado(int first, int pageSize, Map<String, Object> filters) {
        StringBuilder jpql = new StringBuilder("SELECT e FROM " + clazz.getSimpleName() + " e WHERE 1=1");

        if (filters != null) {
            for (String key : filters.keySet()) {
                jpql.append(" AND LOWER(e.").append(key).append(") LIKE LOWER(:").append(key).append(")");
            }
        }

        Query query = em.createQuery(jpql.toString(), clazz);

        if (filters != null) {
            for (Map.Entry<String, Object> entry : filters.entrySet()) {
                query.setParameter(entry.getKey(), "%" + entry.getValue() + "%");
            }
        }

        query.setFirstResult(first);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }

    @Override
    public Long contar(Map<String, Object> filters) {
    	
        StringBuilder jpql = new StringBuilder("SELECT COUNT(e) FROM " + clazz.getSimpleName() + " e WHERE 1=1");

        if (filters != null) {
            for (String key : filters.keySet()) {
                jpql.append(" AND LOWER(e.")
                .append(key)
                .append(") LIKE LOWER(:")
                .append(key)
                .append(")");
            }
        }

        Query query = em.createQuery(jpql.toString());

        if (filters != null) {
            for (Map.Entry<String, Object> entry : filters.entrySet()) {
                query.setParameter(entry.getKey(), "%" + entry.getValue() + "%");
            }
        }

        return (Long) query.getSingleResult();
    }

    /**
     * Obtém o ID da entidade usando API do JPA (padrão enterprise).
     * Substitui reflexão insegura.
     */
	protected Object getEntityId(T entidade) {
			PersistenceUnitUtil util = em.getEntityManagerFactory().getPersistenceUnitUtil();
			return util.getIdentifier(entidade);
	}
}
