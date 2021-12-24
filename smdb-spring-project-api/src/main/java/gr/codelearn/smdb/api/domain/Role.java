package gr.codelearn.smdb.api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Set;

@AllArgsConstructor
@Getter
public enum Role {
	ACTOR("actor"),
	PRODUCER("producer"),
	DIRECTOR("director"),
	WRITER("writer");

	private final String name;

}
