package gr.codelearn.smdb.api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
	ACTOR("actor"),
	PRODUCER("producer"),
	DIRECTOR("director"),
	WRITER("writer");

	private final String name;
}
