package gr.codelearn.smdb.api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
	ACTOR,
	PRODUCER,
	DIRECTOR,
	WRITER;

//	private final String name;
}
