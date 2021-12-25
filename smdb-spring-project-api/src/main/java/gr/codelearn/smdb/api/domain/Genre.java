package gr.codelearn.smdb.api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Genre {
	COMEDY("comedy"),
	SCIFI("sci-fi"),
	HORROR("horror"),
	ROMANCE("romance"),
	ACTION("action"),
	THRILLER("thriller"),
	ANIMATION("animation"),
	CRIME("crime"),
	DRAMA("drama"),
	FANTASY("fantasy"),
	HISTORICAL("historical"),
	WESTERN("western"),
	OTHER("other");

	private final String name;
}
