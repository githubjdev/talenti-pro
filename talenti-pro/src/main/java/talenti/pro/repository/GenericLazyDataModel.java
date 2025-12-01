package talenti.pro.repository;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;

import talenti.pro.service.ServiceInterface;

public class GenericLazyDataModel<T> extends LazyDataModel<T> {

	private static final long serialVersionUID = 1L;

	private final ServiceInterface<?> service;

	private Map<String, Object> fixedFilters;

	public GenericLazyDataModel(ServiceInterface<?> service) {
		this.service = service;
	}

	public void setFixedFilters(Map<String, Object> fixedFilters) {
		this.fixedFilters = fixedFilters;
	}

	@Override
	public int count(Map filterBy) {
		return service.contar(this.fixedFilters).intValue();
	}

	@Override
	public List load(int first, int pageSize, Map sortBy, Map filterBy) {
		setRowCount(service.contar(this.fixedFilters).intValue());
		setPageSize(pageSize);
		setFixedFilters(this.fixedFilters);
		return service.listarPaginado(first, pageSize, this.fixedFilters);
	}

}
