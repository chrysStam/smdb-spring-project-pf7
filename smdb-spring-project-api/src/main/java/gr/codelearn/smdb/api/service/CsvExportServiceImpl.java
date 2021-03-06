package gr.codelearn.smdb.api.service;

import gr.codelearn.smdb.api.base.AbstractLogComponent;
import gr.codelearn.smdb.api.domain.Content;
import gr.codelearn.smdb.api.domain.ContentContributor;
import gr.codelearn.smdb.api.domain.CriticReview;
import gr.codelearn.smdb.api.domain.Film;
import gr.codelearn.smdb.api.domain.Genre;
import gr.codelearn.smdb.api.domain.Person;
import gr.codelearn.smdb.api.domain.TVShow;
import gr.codelearn.smdb.api.repository.ContentRepository;
import gr.codelearn.smdb.api.repository.CriticReviewRepository;
import gr.codelearn.smdb.api.repository.FilmRepository;
import gr.codelearn.smdb.api.repository.PersonRepository;
import gr.codelearn.smdb.api.repository.TVShowRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/* Following this example: https://springhow.com/spring-boot-export-to-csv/ */
@Service
@RequiredArgsConstructor
public class CsvExportServiceImpl extends AbstractLogComponent implements CsvExportService {
	private final PersonRepository personRepository;
	private final ContentRepository contentRepository;
	private final FilmRepository filmRepository;
	private final TVShowRepository tvShowRepository;
	private final CriticReviewRepository criticReviewRepository;

	@Override
	public void exportAll(ZipOutputStream zipOutputStream) throws IOException {
		List<String> tableNames = List.of("people", "contents", "films", "tvshows", "contributors", "genres",
										  "reviews");

		for (String tableName : tableNames) {
			zipOutputStream.putNextEntry(new ZipEntry(tableName + ".csv"));

			CSVPrinter csvPrinter = new CSVPrinter(new OutputStreamWriter(zipOutputStream), CSVFormat.DEFAULT);
			this.export(csvPrinter, tableName);

			csvPrinter.flush();
			zipOutputStream.closeEntry();
		}
	}

	@Override
	public void export(CSVPrinter csvPrinter, String table) throws IOException {
		switch (table) {
			case "people":
				List<Person> people = personRepository.findAll();
				csvPrinter.printRecord("person_id", "name", "surname", "birth_date", "death_date");
				for (Person p : people) {
					csvPrinter.printRecord(p.getId(), p.getName(), p.getSurname(), p.getBirthDate(), p.getDeathDate());
				}
				logger.info("Table [People]: Successfully exported {} rows", people.size());
				return;
			case "contents":
				List<Content> contents = contentRepository.findAll();
				csvPrinter.printRecord("content_id", "title", "imdb_score", "plot_summary", "release_year", "language",
									   "motion_picture_rating");
				for (Content c : contents) {
					csvPrinter.printRecord(c.getId(), c.getTitle(), c.getImdbScore(), c.getPlotSummary(),
										   c.getReleaseYear(), c.getLanguage(), c.getMotionPictureRating());
				}
				logger.info("Table [Contents]: Successfully exported {} rows", contents.size());
				return;
			case "films":
				List<Film> films = filmRepository.findAll();
				csvPrinter.printRecord("content_id", "duration", "budget", "box_office");
				for (Film f : films) {
					csvPrinter.printRecord(f.getId(), f.getDuration(), f.getBudget(), f.getBoxOffice());
				}
				logger.info("Table [Films]: Successfully exported {} rows", films.size());
				return;
			case "tvshows":
				List<TVShow> tvShows = tvShowRepository.findAll();
				csvPrinter.printRecord("content_id", "end_year", "num_seasons");
				for (TVShow tv : tvShows) {
					csvPrinter.printRecord(tv.getId(), tv.getEndYear(), tv.getNumSeasons());
				}
				logger.info("Table [TVShows]: Successfully exported {} rows", tvShows.size());
				return;
			case "contributors":
				List<Content> contentsC = contentRepository.findAll();
				csvPrinter.printRecord("content_id", "person_id", "role");
				int rows = 0;
				for (Content c : contentsC) {
					for (ContentContributor cC : c.getContentContributors()) {
						csvPrinter.printRecord(c.getId(), cC.getKey().getPersonId(), cC.getKey().getRole());
						rows++;
					}
				}
				logger.info("Table [ContentContributors]: Successfully exported {} rows", rows);
				return;
			case "genres":
				List<Content> contentsG = contentRepository.findAll();
				csvPrinter.printRecord("content_id", "genre");
				int rowsFound = 0;
				for (Content c : contentsG) {
					for (Genre g : c.getGenres()) {
						csvPrinter.printRecord(c.getId(), g);
						rowsFound++;
					}
				}
				logger.info("Table [ContentGenres]: Successfully exported {} rows", rowsFound);
				return;
			case "reviews":
				List<CriticReview> criticReviews = criticReviewRepository.findAll();
				csvPrinter.printRecord("critic_review_id", "content_id", "author", "body", "submitted_at", "rating");
				for (CriticReview r : criticReviews) {
					csvPrinter.printRecord(r.getId(),r.getContent().getId(), r.getAuthor(), r.getBody(),
										   r.getSubmittedAt(), r.getRating());
				}
				logger.info("Table [CriticReviews]: Successfully exported {} rows", criticReviews.size());
				return;
			default:
				logger.warn("Invalid table name given, exporting nothing");
				throw new IllegalArgumentException();
		}
	}
}
