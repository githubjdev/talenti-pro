package talenti.pro.repository;

import java.util.List;
import java.util.Map;

public interface GenericRepository<T> {

	List<T> listarPaginado(int first, int pageSize, Map<String, Object> filters);

	Long contar(Map<String, Object> filters);

	void salvar(T entidade);

	void excluir(Long id);

	T buscarPorId(Long id);

}
