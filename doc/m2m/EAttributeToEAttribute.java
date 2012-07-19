package br.pucrs.dslmt.m2m;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

public class EAttributeToEAttribute implements MappingElement {
	private EAttribute source;
	private EAttribute target;
	
	public EAttributeToEAttribute(EAttribute source, EAttribute target) {
		this.source= source;
		this.target= target;
	}
	
	public void map(EObject input, EObject output) {
		EClass inEClass= input.eClass();
		EClass outEClass= output.eClass();
		String sourceName= source.getName();
		String targetName= target.getName();
		Object value= input.eGet(inEClass.getEStructuralFeature(sourceName));
		output.eSet(outEClass.getEStructuralFeature(targetName), value);
	}
	
	public String toString() {
		return "EAttribute: " + source.getName() + " to " + target.getName();
	}
}
