package gr.codelearn.smdb.api.helpers;

import gr.codelearn.smdb.api.domain.Content;
import gr.codelearn.smdb.api.domain.Genre;
import gr.codelearn.smdb.api.transfer.ContentOfContributorByIdByGenreDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ContributorGenre {
	Genre genre;
	List<ContentOfContributorByIdByGenreDto> info;
}
