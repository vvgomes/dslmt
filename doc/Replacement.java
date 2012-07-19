package br.pucrs.dslmt.m2t.filters;

import br.pucrs.dslmt.m2t.Filter;

public class Replacement implements Filter {
	private String target;
	private String replacement;	
	
	public Replacement(String target, String replacement) {
		this.target= target;
		this.replacement= replacement;
	}
	
	public String apply(String input) {
		return input.replaceAll(target, replacement);
	}
}
