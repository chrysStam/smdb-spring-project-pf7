package gr.codelearn.smdb.api.converter;

import gr.codelearn.smdb.api.domain.Genre;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

/* Code snippet based on https://www.baeldung.com/jpa-persisting-enums-in-jpa */
@Converter(autoApply = true)
public class GenreConverter implements AttributeConverter<Genre,String> {

	@Override
	public String convertToDatabaseColumn(Genre genre) {
		if (genre == null) {
			return null;
		}
		return genre.getName();
	}

	@Override
	public Genre convertToEntityAttribute(String name) {
		if (name == null) {
			return null;
		}

		return Stream.of(Genre.values())
					 .filter(g -> g.getName().equals(name))
					 .findFirst()
					 .orElseThrow(IllegalArgumentException::new);
	}
}
