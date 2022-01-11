package gr.codelearn.smdb.api.service;

import gr.codelearn.smdb.api.base.AbstractLogComponent;
import gr.codelearn.smdb.api.domain.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

public abstract class BaseServiceImpl<T extends BaseModel> extends AbstractLogComponent
		implements BaseService<T, Long> {
	public abstract JpaRepository<T, Long> getRepository();

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public T create(final T clazz) {
		logger.trace("Creating {}.", clazz);
		return getRepository().save(clazz);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public void update(final T clazz) {
		logger.trace("Updating {}.", clazz);
		getRepository().save(clazz);
	}

	@Override
	public T find(Long id) {
		return getRepository().findById(id).orElseThrow(NoSuchElementException::new);
	}

	@Override
	public List<T> findAll() {
		logger.trace("Retrieving all data.");
		return getRepository().findAll();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public void deleteById(final Long id) {
		final T entityFound = getRepository().getById(id);
		logger.trace("Deleting {}.", entityFound);
		getRepository().deleteById(id);
	}
}
