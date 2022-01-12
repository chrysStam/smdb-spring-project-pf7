package gr.codelearn.smdb.api.service;

import java.util.List;

public interface BaseService<T, ID> {
	T create(T entity);

	void update(T entity);

	T find(ID id);

	List<T> findAll();

	void deleteById(ID id);
}
