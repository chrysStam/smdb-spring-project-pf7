package gr.codelearn.smdb.api.helpers;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MultiSearch2<T>  {
	String 		type;
	T			infos;
}
