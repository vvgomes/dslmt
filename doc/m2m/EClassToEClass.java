package br.pucrs.dslmt.m2m;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;

public class EClassToEClass implements MappingElement {
	private EClass source;
	private EClass target;
	private Mapping mapping;
	private boolean isRoot= false;
	private List<MappingElement> subElements= new ArrayList<MappingElement>();
	
	public EClassToEClass(EClass source, EClass target, Mapping mapping) {
		this.source= source;
		this.target= target;
		this.mapping= mapping;
	}
	
	public EClassToEClass root() {
		isRoot= true;
		return this;
	}
	
	public EClassToEClass add(MappingElement e) {
		subElements.add(e);
		return this;
	}
	
	public void map(EObject input, EObject output) {		
		EFactory factory= mapping.getTargetMetamodel().getEFactoryInstance();
		EObject model = factory.create(target);
		if(isRoot)
			mapping.setOutputRoot(model);
		else
			((EList)output.eGet(output.eClass().getEStructuralFeature(target.getName()))).add(model);
		
		for(MappingElement each : subElements)
			each.map(input, model);
	}
	
	public EClass getSource() {
		return source;
	}
	
	public EClass getTarget() {
		return target;
	}
	
	public String toString() {
		String s= "EClass: " + source.getName() + " to " + target.getName();
		for(MappingElement each : subElements)
			s+= "\n\t"+ each.toString();
		return s;
	}
}
