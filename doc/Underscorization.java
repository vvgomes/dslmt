package br.pucrs.dslmt.m2t.filters;

import static org.apache.commons.lang.StringUtils.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.pucrs.dslmt.m2t.Filter;

public class Underscorization implements Filter {
	private Pattern pattern= Pattern.compile("^\\w[a-z]+|[A-Z](?:[a-z]+|[A-Z]+)");
	
	public String apply(String input) {
		String[] parts= input.split("[\\s_.]");
		for(int i= 0; i < parts.length; i++)
			parts[i]= applyPattern(parts[i]);
		return join(parts, "_");
	}
	
	private String applyPattern(String str) {
		Matcher matcher= pattern.matcher(str);
		StringBuffer buffer= new StringBuffer();
		while(matcher.find())
			buffer.append(lowerCase(matcher.group())+"_");
		return buffer.toString().substring(0, buffer.length()-1);
	}
}
