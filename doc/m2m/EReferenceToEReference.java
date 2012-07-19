package br.pucrs.dslmt.m2m;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

public class EReferenceToEReference implements MappingElement {
	private EReference source;
	private EReference target;
	private Mapping mapping;
	
	public EReferenceToEReference(EReference source, EReference target, Mapping mapping) {
		this.source = source;
		this.target = target;
		this.mapping= mapping;
	}

	public void map(EObject input, EObject output) {
		EClass inEClass= input.eClass();
		String sourceName= source.getName();
		String targetName= target.getName();
		EStructuralFeature f= inEClass.getEStructuralFeature(sourceName);
		EList children= (EList) input.eGet(f);
		for(Object child : children) {
			MappingElement e= mapping.getMappingFor(sourceName, targetName);
			e.map((EObject)child, output);
		}
	}
	
	public String toString() {
		return "EReference: " + source.getName() + " to " + target.getName();
	}
}
