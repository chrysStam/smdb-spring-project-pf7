package gr.codelearn.smdb.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;    // Explicitly declared, recommended by java docs

	@Id    // Marks field as Primary Key
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idGenerator")
	private Long id;
}
