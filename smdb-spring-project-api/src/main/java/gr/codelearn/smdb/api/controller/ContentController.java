package gr.codelearn.smdb.api.controller;

import gr.codelearn.smdb.api.domain.Content;
import gr.codelearn.smdb.api.domain.Genre;
import gr.codelearn.smdb.api.domain.Role;
import gr.codelearn.smdb.api.service.ContentService;
import gr.codelearn.smdb.api.service.PersonService;
import gr.codelearn.smdb.api.transfer.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.Callable;

@RestController
@RequestMapping("/contents")
public abstract class ContentController<T extends Content> extends AbstractController<T> {
	@Autowired
	private PersonService personService;

	@Override
	protected abstract ContentService<T> getBaseService();

	@GetMapping(params = {"title"})
	public Callable<ResponseEntity<ApiResponse<List<T>>>> findByTitle(@RequestParam("title") String title) {
		return () -> ResponseEntity.ok(
				ApiResponse.<List<T>>builder().data(getBaseService().findByTitle(title)).build());
	}

	@GetMapping(path = "search", params = {"title"})
	public Callable<ResponseEntity<ApiResponse<List<T>>>> searchInTitle(@RequestParam("title") String title) {
		return () -> ResponseEntity.ok(
				ApiResponse.<List<T>>builder().data(getBaseService().findByTitleContainingIgnoreCase(title)).build());
	}

	@GetMapping(params = {"top"})
	public Callable<ResponseEntity<ApiResponse<List<T>>>> findTopXByImdbScore(@RequestParam("top") Integer num) {
		return () -> ResponseEntity.ok(
				ApiResponse.<List<T>>builder().data(getBaseService().findTopXOrderedByImdbScore(num)).build());
	}

	@GetMapping(params = {"name", "surname"})
	public Callable<ResponseEntity<ApiResponse<List<T>>>> findByContributorByFullName(@RequestParam("name") String name,
																					  @RequestParam("surname") String surname) {
		return () -> ResponseEntity.ok(
				ApiResponse.<List<T>>builder().data(getBaseService().findByContributorByFullName(name, surname))
						   .build());
	}

	@GetMapping(params = {"name", "surname", "role"})
	public Callable<ResponseEntity<ApiResponse<List<T>>>> findByContributorByFullNameAndRole(
			@RequestParam("name") String name, @RequestParam("surname") String surname,
			@RequestParam("role") Role role) {
		return () -> ResponseEntity.ok(ApiResponse.<List<T>>builder()
												  .data(getBaseService().findByContributorByFullNameAndRole(name,
																											surname,
																											role))
												  .build());
	}

	@GetMapping(params = {"genre"})
	public Callable<ResponseEntity<ApiResponse<List<T>>>> findByGenre(@RequestParam("genre") Genre genre) {
		return () -> ResponseEntity.ok(
				ApiResponse.<List<T>>builder().data(getBaseService().findAllByGenresContaining(genre)).build());
	}

	/* Content Contributor add/delete */

	@PostMapping(path = "/{contentId}/contributors", params = {"personId", "role"})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void addContributor(@PathVariable("contentId") Long contentId, @RequestParam("personId") Long personId,
							   @RequestParam("role") Role role) {
		T content = getBaseService().find(contentId);
		getBaseService().addContributor(content, personService.find(personId), role);
		getBaseService().update(content);
	}

	@DeleteMapping(path = "/{contentId}/contributors", params = {"personId", "role"})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteContributor(@PathVariable("contentId") Long contentId, @RequestParam("personId") Long personId,
							   @RequestParam("role") Role role) {
		T content = getBaseService().find(contentId);
		getBaseService().deleteContributor(content, personService.find(personId), role);
	}

	/* Content Genre add/delete */

	@PostMapping(path = "/{contentId}/genres", params = {"genre"})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void addGenre(@PathVariable("contentId") Long contentId, @RequestParam("genre") Genre genre) {
		T content = getBaseService().find(contentId);
		getBaseService().addGenre(content, genre);
		getBaseService().update(content);
	}

	@DeleteMapping(path = "/{contentId}/genres", params = {"genre"})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteGenre(@PathVariable("contentId") Long contentId, @RequestParam("genre") Genre genre) {
		T content = getBaseService().find(contentId);
		getBaseService().deleteGenre(content, genre);
		getBaseService().update(content);
	}
}
