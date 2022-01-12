package gr.codelearn.smdb.api.helpers;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MultiSearch<T>  {
	String 		type;
	List<T> 	infos;
}
