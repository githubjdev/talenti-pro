package talenti.pro.service.cadastro;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ServiceInterface<T> {

	List<T> listarPaginado(int first, int pageSize, Map<String, Object> filters);

	Long contar(Map<String, Object> filters);

	T salvar(T entidade);

	void excluir(Long id);

	T buscarPorId(Long id);

	Optional<T> buscarPorIdOpt(Long id);

}
