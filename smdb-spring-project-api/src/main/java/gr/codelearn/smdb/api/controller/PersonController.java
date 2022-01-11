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

@RestController
@RequiredArgsConstructor
@RequestMapping("/people")
public class PersonController extends AbstractController<Person> {
	private final PersonService personService;

	@Override
	protected BaseService<Person, Long> getBaseService() {
		return personService;
	}

	@GetMapping(path = "/{pId}/contents", params = "role", headers = "action=getContributionsOfPersonByIdByRole",
			produces =
			"application/vnd.app-v1+json")
	public ResponseEntity<ApiResponse<List<Content>>> getContributionsOfPersonByIdByRole(
			@PathVariable(value = "pId") Long personId,
			@RequestParam("role") Role role) {
		return ResponseEntity.ok(ApiResponse.<List<Content>>builder()
											.data(personService.getContributionsOfPersonByIdByRole(personId, role)).build());
	}

	@GetMapping(path = "/{pId}/tvshows", headers = "action=getTVShowContributionsOfPersonById", produces =
			"application/vnd.app-v1+json")
	public ResponseEntity<ApiResponse<List<TVShow>>> getTVShowContributionsOfPersonById(
			@PathVariable(value = "pId") Long personId) {
		return ResponseEntity.ok(ApiResponse.<List<TVShow>>builder()
											.data(personService.getTVShowContributionsOfPersonById(personId)).build());
		}

	@GetMapping(path = "/{pId}/films", headers = "action=getFilmContributionsOfPersonById", produces =
			"application/vnd.app-v1+json")
	public ResponseEntity<ApiResponse<List<Film>>> getFilmContributionsOfPersonById(
			@PathVariable(value = "pId") Long personId) {
		return ResponseEntity.ok(ApiResponse.<List<Film>>builder()
											.data(personService.getFilmContributionsOfPersonById(personId)).build());
	}

	@GetMapping(path = "/{pId}/contents", headers = "action=getContributionsOfPersonById", produces =
			"application/vnd.app-v1+json")
	public ResponseEntity<ApiResponse<List<Content>>> getContributionsOfPersonById(
			@PathVariable(value = "pId") Long personId) {
		return ResponseEntity.ok(ApiResponse.<List<Content>>builder()
											.data(personService.getContributionsOfPersonById(personId)).build());
	}


}
