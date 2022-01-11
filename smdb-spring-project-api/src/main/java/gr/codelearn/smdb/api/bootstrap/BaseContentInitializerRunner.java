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
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.sql.Date;

@Component
//@Profile("base-content-initializer")
@RequiredArgsConstructor
public class BaseContentInitializerRunner extends AbstractLogComponent implements CommandLineRunner {
	private final PersonService personService;
	private final FilmService filmService;
	private final TVShowService tvShowService;

	@Override
	public void run(final String... args) {
		/* Add People */

		Person person1 = personService.create(Person.builder().name("Brad").surname("Pitt").birthDate(
				Date.valueOf("1963-12-18")).build());
		Person person2 = personService.create(Person.builder().name("Leonardo").surname("DiCaprio").birthDate(
				Date.valueOf("1974-11-11")).build());
		Person person3 = personService.create(Person.builder().name("Quentin").surname("Tarantino").birthDate(
				Date.valueOf("1963-03-27")).build());

		/* Add Films & TVShows */

		Film film =
				Film.builder().title("Once Upon a Time in... Hollywood").imdbScore(7.6).plotSummary("A faded television actor and his stunt double strive to achieve fame and success in the final years of Hollywood's Golden Age in 1969 Los Angeles.")
					.releaseYear(2019).language("english").motionPictureRating(MotionPictureRating.RESTRICTED).duration(161).motionPictureRating(
						MotionPictureRating.RESTRICTED).budget(BigInteger.valueOf(90000000)).boxOffice(BigInteger.valueOf(374565574)).build();
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

		// Adding a critic review
		CriticReview criticReview =
				CriticReview.builder().author("Theodoros Mystiloglou").body("It was ok.").rating(7.2).build();
		filmService.addCriticReview(film, criticReview);
		criticReview =
				CriticReview.builder().author("Mike Daskalantonakis").body("I did not enjoy it.").rating(5.4).build();
		filmService.addCriticReview(film, criticReview);
		criticReview =
				CriticReview.builder().author("Dimitra Koumparaki").body("Amazing!").rating(9.1).build();
		filmService.addCriticReview(film, criticReview);

		filmService.update(film);

		TVShow tvShow = TVShow.builder().title("Game of Thrones").releaseYear(2005).numSeasons(8).build();
		tvShowService.addGenre(tvShow, Genre.FANTASY);
		tvShowService.addGenre(tvShow, Genre.ACTION);
		tvShowService.create(tvShow);


	}
}
