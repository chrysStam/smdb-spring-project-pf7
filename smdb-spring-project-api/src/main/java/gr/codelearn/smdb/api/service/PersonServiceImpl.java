package gr.codelearn.smdb.api.service;

import gr.codelearn.smdb.api.domain.Person;
import gr.codelearn.smdb.api.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl extends BaseServiceImpl<Person> implements PersonService {
	private final PersonRepository personRepository;

	@Override
	public JpaRepository<Person, Long> getRepository() {
		return personRepository;
	}
}
