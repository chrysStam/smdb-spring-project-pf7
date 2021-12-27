package gr.codelearn.smdb.api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/* Based on the US motion picture content rating system */
@AllArgsConstructor
@Getter
public enum MotionPictureRating {
	GENERAL_AUDIENCES("G"),
	PARENTAL_GUIDANCE_SUGGESTED("PG"),
	PARENTS_STRONGLY_CAUTIONED("PG-13"),
	RESTRICTED("R"),
	ADULTS_ONLY("NC-17");

	private final String name;
}
