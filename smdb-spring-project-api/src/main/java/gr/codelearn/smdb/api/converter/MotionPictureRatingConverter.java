package gr.codelearn.smdb.api.converter;

import gr.codelearn.smdb.api.domain.MotionPictureRating;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

/* Code snippet based on https://www.baeldung.com/jpa-persisting-enums-in-jpa */
@Converter(autoApply = true)
public class MotionPictureRatingConverter implements AttributeConverter<MotionPictureRating,String> {

	@Override
	public String convertToDatabaseColumn(MotionPictureRating motionPictureRating) {
		if (motionPictureRating == null) {
			return null;
		}
		return motionPictureRating.getName();
	}

	@Override
	public MotionPictureRating convertToEntityAttribute(String name) {
		if (name == null) {
			return null;
		}

		return Stream.of(MotionPictureRating.values())
					 .filter(r -> r.getName().equals(name))
					 .findFirst()
					 .orElseThrow(IllegalArgumentException::new);
	}
}
