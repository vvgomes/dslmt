package br.pucrs.dslmt.textualSpec;

import org.eclipse.emf.ecore.EAttribute;

public class ConstantSpec implements SpecElement {
	private String text;
	private String grammarHead= "'";
	private String grammarTail= "'";
	private String templateHead= "";
	private String templateTail= "";
	private EAttribute associated;
	
	public ConstantSpec(String text) {
		this.text= text;
	}
	
	public ConstantSpec optional() {
		grammarHead= "(" + grammarHead;
		grammarTail= grammarTail + ")?";
		return this;
	}
	
	public ConstantSpec associatedWith(EAttribute associated) {
		this.associated= associated;
		grammarTail= "' {$node.eSet(eClass.getEStructuralFeature(\""+associated.getName()+"\"), true);})?";
		templateHead= "$if(" + associated.getName() + ")$";
		templateTail= "$endif$";
		return this;
	}
	
	public String getGrammarFragment() { 
		return grammarHead + text + grammarTail;
	}
	
	public String getTemplateFragment() {
		return templateHead + parseSpecialChars() + templateTail;
	}
	
	private String parseSpecialChars() {
		String s= text;
		s= parseSpecialChar("\\n", s);
		s= parseSpecialChar("\\t", s);
		return s;
	}

	
	private String parseSpecialChar(String toParse, String str) {
		char c= (toParse.equals("\\n"))? '\n' : '\t';
		String s= str;
		int i= s.indexOf(toParse);
		while(i != -1) {
			s= s.substring(0, i) + c + s.substring(i+2, s.length());
			i= s.indexOf(toParse);
		}
		return s;
	}
	
	public EAttribute getAssociatedAttr() {
		return associated;
	}
}
