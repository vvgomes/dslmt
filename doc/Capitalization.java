package br.pucrs.dslmt.m2t.filters;

import static org.apache.commons.lang.StringUtils.*;
import br.pucrs.dslmt.m2t.Filter;

public class Capitalization implements Filter {
	
	public String apply(String input) {
		String[] parts= input.split("[\\s_.]");
		for(int i= 0; i < parts.length; i++)
			parts[i]= capitalize(parts[i]);
		return join(parts);
	}
}
