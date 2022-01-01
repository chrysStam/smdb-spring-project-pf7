package gr.codelearn.smdb.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CONTENT_CONTRIBUTORS")
public class ContentContributor implements Serializable {
	@EmbeddedId
	private ContentContributorKey key;

	@ManyToOne
	@JoinColumn(name = "content_id")
	@MapsId("contentId")
	private Content content;

	@ManyToOne
	@JoinColumn(name = "person_id")
	@MapsId("personId")
	private Person person;
}
