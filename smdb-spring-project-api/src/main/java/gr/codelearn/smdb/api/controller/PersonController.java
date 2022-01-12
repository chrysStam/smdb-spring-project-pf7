package gr.codelearn.smdb.api.controller;

import gr.codelearn.smdb.api.domain.Content;
import gr.codelearn.smdb.api.domain.Film;
import gr.codelearn.smdb.api.domain.Person;
import gr.codelearn.smdb.api.domain.Role;
import gr.codelearn.smdb.api.domain.TVShow;
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


	@GetMapping(path = "/{pId}/tvshows", headers = "action=getTVShowContributionsOfPersonById")
	public ResponseEntity<ApiResponse<List<TVShow>>> getTVShowContributionsOfPersonById(
			@PathVariable(value = "pId") Long personId) {
		return ResponseEntity.ok(ApiResponse.<List<TVShow>>builder()
											.data(personService.getTVShowContributionsOfPersonById(personId)).build());
		}

	@GetMapping(path = "/{pId}/films", headers = "action=getFilmContributionsOfPersonById")
	public ResponseEntity<ApiResponse<List<Film>>> getFilmContributionsOfPersonById(
			@PathVariable(value = "pId") Long personId) {
		return ResponseEntity.ok(ApiResponse.<List<Film>>builder()
											.data(personService.getFilmContributionsOfPersonById(personId)).build());
	}

	@GetMapping(headers = "action=getPersonWithMostContributions")
	public Callable<ResponseEntity<ApiResponse<Person>>> getPersonWithMostContributions() {
		return ()-> ResponseEntity.ok(ApiResponse.<Person>builder()
											.data(personService.getPersonWithMostContributions()).build());
	}

}
