package br.pucrs.dslmt.textualSpec;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;

public class EClassSpec {
	private EClass eClass;
	private List<SpecElement> elements= new ArrayList<SpecElement>();
	
	public EClassSpec(EClass eClass) {
		this.eClass= eClass;
	}
	
	public EClassSpec add(SpecElement e) {
		elements.add(e);
		return this;
	}
	
	public EClass getEClass() {
		return eClass;
	}
	
	public String getGrammarRule() {
		String name= eClass.getName();
		
		String head=
			name+" returns [EObject node]\n"+
			"\t@init {\n"+
			"\tEClass eClass= (EClass)pkg.getEClassifier(\""+name+"\");\n"+
			"\t$node= pkg.getEFactoryInstance().create(eClass);\n"+
			"\t}\n"+
			"\t:\n";
		
		String body= "";
		for(SpecElement e : elements)
			body+= "\t" + e.getGrammarFragment() + "\n";
		
		String tail= "\t;";
		
		return head + body + tail;
	}
	
	public String getTemplate() {
		String template= ""; 
		for(SpecElement e : elements)
			template+= e.getTemplateFragment();
		return template;
	}
	
	public List<SpecElement> getElements() {
		return elements;
	}
}
