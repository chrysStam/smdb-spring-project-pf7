package gr.codelearn.smdb.api.converter;

import gr.codelearn.smdb.api.domain.Role;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

/* Code snippet based on https://www.baeldung.com/jpa-persisting-enums-in-jpa */
@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role,String> {

	@Override
	public String convertToDatabaseColumn(Role role) {
		if (role == null) {
			return null;
		}
		return role.getName();
	}

	@Override
	public Role convertToEntityAttribute(String name) {
		if (name == null) {
			return null;
		}

		return Stream.of(Role.values())
					 .filter(r -> r.getName().equals(name))
					 .findFirst()
					 .orElseThrow(IllegalArgumentException::new);
	}
}
