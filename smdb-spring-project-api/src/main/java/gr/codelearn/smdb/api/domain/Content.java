package gr.codelearn.smdb.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity()
@Table(name = "CONTENTS")
@SequenceGenerator(name = "idGenerator", sequenceName = "CONTENTS_SEQ", initialValue = 1, allocationSize = 1)
@Inheritance(strategy = InheritanceType.JOINED)
public class Content extends BaseModel{
	@NotNull
	private String title;
	private Double rating;
	@Column(name="plot_summary")
	private String plotSummary;
	@Column(name="release_year")
	private Integer releaseYear;
	private String language;
	@Column(columnDefinition = "LONGVARCHAR")
	@Enumerated(EnumType.STRING)
	@ElementCollection
//	@CollectionTable
	private Set<Genre> genres;
//	private Float imdbScore;

	@OneToMany(mappedBy = "content")
	private Set<ContentContributor> contributors;
}
