package br.pucrs.dslmt.m2t.filters;

import br.pucrs.dslmt.m2t.Filter;

public class DoubleQuotation implements Filter {
	public String apply(String str) {
		return "\""+str+"\"";
	}
}
