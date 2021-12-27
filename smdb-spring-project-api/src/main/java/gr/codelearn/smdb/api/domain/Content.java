package gr.codelearn.smdb.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
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
@SequenceGenerator(name = "idGenerator", sequenceName = "CONTENTS_SEQ", initialValue = 1, allocationSize = 1)
@Inheritance(strategy = InheritanceType.JOINED)
public class Content extends BaseModel{
	@NotNull
	@Column(length = 256)
	private String title;

	@Column(precision = 3, scale = 1)
	private Double rating;
//	private Float imdbScore;

	@Column(length = 4096, name="plot_summary")
	private String plotSummary;

	@Column(name="release_year")
	private Integer releaseYear;

	@Column(length = 30)
	private String language;

//	@Column(columnDefinition = "LONGVARCHAR")
	@Enumerated(EnumType.STRING)
	@ElementCollection		// Implements a one-to-many relationship with simple, non-entity types
//	@CollectionTable		// Specifies the properties of the table that is created
	private final Set<Genre> genres = new HashSet<>();

	@OneToMany(mappedBy = "content")
	private final Set<ContentContributor> contentContributors = new HashSet<>();
}
