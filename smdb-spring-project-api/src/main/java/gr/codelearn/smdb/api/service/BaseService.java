package gr.codelearn.smdb.api.service;

import java.util.List;

public interface BaseService<T, ID> {
	T create(T clazz);

	List<T> findAll();
}
