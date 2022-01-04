package gr.codelearn.smdb.api.service;

import gr.codelearn.smdb.api.base.AbstractLogComponent;
import gr.codelearn.smdb.api.domain.Person;
import gr.codelearn.smdb.api.repository.FilmRepository;
import gr.codelearn.smdb.api.repository.PersonRepository;
import gr.codelearn.smdb.api.repository.TVShowRepository;
import gr.codelearn.smdb.api.transfer.KeyValue;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

/* Following this example: https://springhow.com/spring-boot-export-to-csv/ */
@Service
@RequiredArgsConstructor
public class CsvExportServiceImpl extends AbstractLogComponent implements CsvExportService {
	private final PersonRepository personRepository;
	private final FilmRepository filmRepository;
	private final TVShowRepository tvShowRepository;

	@Override
	public List<KeyValue<String,Integer>> exportAll(Writer writer) {
		return null;
	}

	@Override
	public KeyValue<String, Integer> exportPeople(Writer writer) {
		List<Person> people = personRepository.findAll();
		// Can add column names on the first row of the csv if wanted
		try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
			for (Person p : people) {
				csvPrinter.printRecord(p.getId(), p.getName(), p.getSurname(), p.getBirthDate(), p.getDeathDate());
			}
			logger.info("Table [Person]: Successfully exported {} rows", people.size());
		} catch (IOException e) {
			logger.error("Error while writing to CSV file", e);
		}

		return new KeyValue<>("[Person] exported rows", people.size());
	}
}
