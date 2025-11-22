package br.tec.ici.saude.Repository;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class GenericLazyDataModel<T> extends LazyDataModel<T> {

	private static final long serialVersionUID = 1L;

	private final GenericRepository<T> repository;

	private Map<String, Object> fixedFilters;

	public GenericLazyDataModel(GenericRepository<T> repository) {
		this.repository = repository;
	}

	public void setFixedFilters(Map<String, Object> fixedFilters) {
		this.fixedFilters = fixedFilters;
	}

	@Override
	public int count(Map filterBy) {
		return repository.contar(this.fixedFilters).intValue();
	}

	@Override
	public List load(int first, int pageSize, Map sortBy, Map filterBy) {
		setRowCount(repository.contar(this.fixedFilters).intValue());
		setPageSize(pageSize);
		setFixedFilters(this.fixedFilters);
		return repository.listarPaginado(first, pageSize, this.fixedFilters);
	}

}
