package gr.codelearn.smdb.api.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name = "CONTENT_CONTRIBUTORS")
@SequenceGenerator(name = "idGenerator", sequenceName = "CONTENT_CONTRIBUTORS_SEQ", initialValue = 1, allocationSize = 1)
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
