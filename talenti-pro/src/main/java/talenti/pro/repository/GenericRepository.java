package talenti.pro.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface GenericRepository<T extends Serializable> {

	List<T> listarPaginado(int first, int pageSize, Map<String, Object> filters);

	Long contar(Map<String, Object> filters);

	T salvar(T entidade);

	void excluir(Long id);

	T buscarPorId(Long id);

	Optional<T> buscarPorIdOpt(Long id);

	int executeUpdateNativeSQL(String sql);
	
	String getTableName();
	
	String getEntityName();

}
