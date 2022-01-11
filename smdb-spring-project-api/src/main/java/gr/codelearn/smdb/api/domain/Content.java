package gr.codelearn.smdb.api.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity()
@Table(name = "CONTENTS")
@SequenceGenerator(name = "idGenerator", sequenceName = "CONTENTS_SEQ", initialValue = 21, allocationSize = 1)
@Inheritance(strategy = InheritanceType.JOINED)
public class Content extends BaseModel{
	@NotNull
	@Column(length = 256)
	private String title;

	@DecimalMin(value = "0.0", inclusive = true)
	@DecimalMax(value = "10.0", inclusive = true)
	@Digits(integer = 2, fraction = 1)
	@Column(name = "imdb_score")
	private Double imdbScore;

	@Column(length = 4096, name="plot_summary")
	private String plotSummary;

	@NotNull
	@Column(name="release_year")
	@Min(1878)	// Year of first motion picture content ever made
	private Integer releaseYear;

	@Column(length = 30)
	private String language;

	@Column(name = "motion_picture_rating")
	private MotionPictureRating motionPictureRating;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	private final Set<Genre> genres = new HashSet<>();

	@JsonManagedReference("contentContributors")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "content", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private final Set<ContentContributor> contentContributors = new HashSet<>();

	@JsonManagedReference("criticReviews")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "content", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private final Set<CriticReview> criticReviews = new HashSet<>();
}
