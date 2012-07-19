package br.pucrs.dslmt.textualSpec;

import org.eclipse.emf.ecore.EReference;

public class EReferenceSpec implements SpecElement {
	private EReference reference;
	private String separator;
	
	public EReferenceSpec(EReference reference) {
		this.reference= reference;
	}
	
	public EReferenceSpec withSeparator(String separator) {
		this.separator= separator;
		return this;
	}
	
	public String getGrammarFragment() {
		String name= reference.getName();
		String basic= "r="+name+" {((EList)$node.eGet(eClass.getEStructuralFeature(\""+name+"\"))).add($r.node);}";
		if(separator == null)
			return "("+ basic +")*";
		return "(" + basic + ")? ('" + separator + "' " + basic + ")*";
	}
	
	public String getTemplateFragment() {
		String name= reference.getName();
		if(separator == null)
			return "$" + name + "; separator=\"\\n\"$";
		return "$" + name + "; separator=\"" + separator + "\"$";
	}
	
	public String getEReferenceName() {
		return reference.getName();
	}
}
