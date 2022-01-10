package gr.codelearn.smdb.api.helpers;

import gr.codelearn.smdb.api.transfer.NoOfContentPerGenreDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class YearGenresStats {
	Integer year;
	List<NoOfContentPerGenreDto> stats;

}
