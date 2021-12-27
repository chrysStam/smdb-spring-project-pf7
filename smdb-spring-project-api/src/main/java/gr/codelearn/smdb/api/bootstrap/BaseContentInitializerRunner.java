package gr.codelearn.smdb.api.bootstrap;

import gr.codelearn.smdb.api.base.AbstractLogComponent;
import gr.codelearn.smdb.api.domain.Film;
import gr.codelearn.smdb.api.domain.Genre;
import gr.codelearn.smdb.api.domain.Person;
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
		Person person = personService.create(Person.builder().name("Theodoros").surname("DiMystiloglou").birthDate(
				Date.valueOf("1997-11-30")).build());
		logger.info("Created {}.", person);
		Person person2 = personService.create(Person.builder().name("Leonardo").surname("DiCaprio").birthDate(
				Date.valueOf("1974-11-11")).build());
		logger.info("Created {}.", person2);
		Person person3 = personService.create(Person.builder().name("Quentin").surname("Tarantino").birthDate(
				Date.valueOf("1963-03-27")).build());
		logger.info("Created {}.", person3);

		/* Add Films */

		Film film = Film.builder().title("Once Upon a Time in... Hollywood").duration(182).build();

		filmService.addGenre(film, Genre.DRAMA);
		filmService.addGenre(film, Genre.COMEDY);
		filmService.create(film);

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
