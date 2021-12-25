package gr.codelearn.smdb.api.service;

import gr.codelearn.smdb.api.base.AbstractLogComponent;
import gr.codelearn.smdb.api.domain.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

public abstract class BaseServiceImpl<T extends BaseModel> extends AbstractLogComponent
		implements BaseService<T, Long> {
	public abstract JpaRepository<T, Long> getRepository();

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public T create(final T clazz) {
		logger.trace("Creating {}.", clazz);
		return getRepository().save(clazz);
	}
}
