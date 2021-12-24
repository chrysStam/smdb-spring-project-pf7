package gr.codelearn.smdb.api.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Set;

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
