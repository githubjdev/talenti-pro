package talenti.pro.repository;

import java.util.List;
import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@SuppressWarnings("unchecked")
public abstract class GenericRepositoryImpl<T> implements GenericRepository<T> {

    @PersistenceContext(unitName = "talenti-pro-pu")
    protected EntityManager em;

    private final Class<T> clazz;

    public GenericRepositoryImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    @Transactional
    public void salvar(T entidade) {
        if (getId(entidade) != null) {
            em.merge(entidade);
        } else {
            em.persist(entidade);
        }
    }

    @Override
    @Transactional
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
                jpql.append(" AND LOWER(e.").append(key).append(") LIKE LOWER(:").append(key).append(")");
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
     * Método auxiliar para obter o ID de qualquer entidade.
     */
    private Object getId(T entity) {
        try {
            return entity.getClass().getMethod("getId").invoke(entity);
        } catch (Exception e) {
            return null;
        }
    }
}
