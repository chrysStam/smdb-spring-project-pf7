package gr.codelearn.smdb.api.bootstrap;

import gr.codelearn.smdb.api.base.AbstractLogComponent;
import gr.codelearn.smdb.api.domain.CriticReview;
import gr.codelearn.smdb.api.domain.Film;
import gr.codelearn.smdb.api.domain.Genre;
import gr.codelearn.smdb.api.domain.MotionPictureRating;
import gr.codelearn.smdb.api.domain.Person;
import gr.codelearn.smdb.api.domain.Role;
import gr.codelearn.smdb.api.domain.TVShow;
import gr.codelearn.smdb.api.service.FilmService;
import gr.codelearn.smdb.api.service.PersonService;
import gr.codelearn.smdb.api.service.TVShowService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.aspectj.weaver.ast.Call;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;

@Component
@Profile("base-content-initializer")
@RequiredArgsConstructor
public class BaseContentInitializerRunner extends AbstractLogComponent implements CommandLineRunner {
	private final PersonService personService;
	private final FilmService filmService;
	private final TVShowService tvShowService;

	@Override
	public void run(final String... args) throws Exception {
		/* Add People */

		Person person1 = personService.create(Person.builder().name("Brad").surname("Pitt").birthDate(
				Date.valueOf("1963-12-18")).build());
		Person person2 = personService.create(Person.builder().name("Leonardo").surname("DiCaprio").birthDate(
				Date.valueOf("1974-11-11")).build());
		Person person3 = personService.create(Person.builder().name("Quentin").surname("Tarantino").birthDate(
				Date.valueOf("1963-03-27")).build());

		/* Add Films & TVShows */

		Film film =	Film.builder().title("Once Upon a Time in... Hollywood").duration(182).motionPictureRating(
						MotionPictureRating.RESTRICTED).build();
		filmService.addGenre(film, Genre.DRAMA);
		filmService.addGenre(film, Genre.COMEDY);

		// This will be ignored since genre is already added
		filmService.addGenre(film, Genre.DRAMA);

		film = filmService.create(film);

		// Now that film's id has been generated, we can populate ContentContributor table with contributors
		filmService.addContributor(film, person1, Role.ACTOR);
		filmService.addContributor(film, person2, Role.ACTOR);
		filmService.addContributor(film, person3, Role.DIRECTOR);
		filmService.addContributor(film, person3, Role.PRODUCER);	// A person can have multiple roles

		// This will be ignored since (person,role) tuple is already added
		filmService.addContributor(film, person2, Role.ACTOR);

		filmService.update(film);

		// Adding a critic review
		CriticReview criticReview =
				CriticReview.builder().author("Theodoros Mystiloglou").body("It was ok.").rating(7.2).build();
		filmService.addCriticReview(film, criticReview);

		filmService.update(film);

		TVShow tvShow = TVShow.builder().title("Game of Thrones").numSeasons(8).build();
		tvShowService.addGenre(tvShow, Genre.FANTASY);
		tvShowService.addGenre(tvShow, Genre.ACTION);
		tvShowService.create(tvShow);

		/* Export to CSV code - probably needs its own dedicated ExportService and a function for each domain class
		*  which return the rows exported. Probably will have /person/export etc... and /exportAll REST calls */

		// Following this example: https://springhow.com/spring-boot-export-to-csv/

		// Person export:

		FileWriter fileWriter = new FileWriter("people.csv");	// Will be replaced by controller writer

		List<Person> people = personService.findAll();
		// Can add column names on the first row of the csv if wanted
		try (CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT)) {
			for (Person p : people) {
				csvPrinter.printRecord(p.getId(), p.getName(), p.getSurname(), p.getBirthDate(), p.getDeathDate());
			}
			logger.info("Table [Person]: Successfully exported {} rows", people.size());
		} catch (IOException e) {
			logger.error("Error while writing to CSV file", e);
		}

	}
}
