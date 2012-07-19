package br.pucrs.dslmt.m2m;

import org.eclipse.emf.ecore.EObject;

public interface MappingElement {
	public void map(EObject input, EObject output);
}
