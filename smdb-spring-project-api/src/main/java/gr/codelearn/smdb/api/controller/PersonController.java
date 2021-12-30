package gr.codelearn.smdb.api.controller;

import gr.codelearn.smdb.api.domain.Person;
import gr.codelearn.smdb.api.service.BaseService;
import gr.codelearn.smdb.api.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/people")
public class PersonController extends AbstractController<Person> {
	private final PersonService personService;

	@Override
	protected BaseService<Person, Long> getBaseService() {
		return personService;
	}
}
