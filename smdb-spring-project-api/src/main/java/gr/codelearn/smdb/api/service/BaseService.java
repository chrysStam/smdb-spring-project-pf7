package gr.codelearn.smdb.api.service;

import java.util.List;

public interface BaseService<T, ID> {
	T create(T clazz);

	void update(T clazz);

	T find(ID id);

	List<T> findAll();
}
