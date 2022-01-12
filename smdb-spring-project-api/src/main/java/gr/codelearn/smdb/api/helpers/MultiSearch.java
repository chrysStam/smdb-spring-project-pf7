package gr.codelearn.smdb.api.helpers;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MultiSearch<T>  {
	String 		type;
	T			infos;
}
