package gr.codelearn.smdb.api.controller;

import gr.codelearn.smdb.api.domain.Person;
import gr.codelearn.smdb.api.service.BaseService;
import gr.codelearn.smdb.api.service.PersonService;
import gr.codelearn.smdb.api.transfer.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.Callable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/people")
public class PersonController extends AbstractController<Person> {
	private final PersonService personService;

	@Override
	protected BaseService<Person, Long> getBaseService() {
		return personService;
	}

	@GetMapping(headers = "action=getPersonWithMostContributions")
	public Callable<ResponseEntity<ApiResponse<Person>>> getPersonWithMostContributions() {
		return ()-> ResponseEntity.ok(ApiResponse.<Person>builder()
											.data(personService.getPersonWithMostContributions()).build());
	}

	@GetMapping(path="/contents/{cId}", headers = "action=getPeopleOfSpecificContent")
	public Callable<ResponseEntity<ApiResponse<List<Person>>>> getPeopleOfSpecificContent(@PathVariable("cId") Long contentId) {
		return ()-> ResponseEntity.ok(ApiResponse.<List<Person>>builder()
											.data(personService.getPeopleOfSpecificContent(contentId)).build());
	}

}
