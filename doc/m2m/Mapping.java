package br.pucrs.dslmt.m2m;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

public class Mapping {
	private EPackage sourceMetamodel;
	private EPackage targetMetamodel;
	private Map<ComplexKey, EClassToEClass> elements= new LinkedHashMap<ComplexKey, EClassToEClass>();
	private EObject output;
	
	public Mapping(EPackage sourceMetamodel, EPackage targetMetamodel) {
		this.sourceMetamodel = sourceMetamodel;
		this.targetMetamodel = targetMetamodel;
	}
	
	public void map(EObject model) {
		if(elements.size() == 0)
			return;
		
		EClassToEClass root= elements.values().iterator().next();
		root.map(model, output);
	}
	
	public Mapping add(EClassToEClass e) {
		elements.put(new ComplexKey(e), e);
		return this;
	}
	
	public EPackage getTargetMetamodel() {
		return targetMetamodel;
	}
	
	public EPackage getSourceMetamodel() {
		return sourceMetamodel;
	}
	
	public void setOutputRoot(EObject root) {
		output= root;
	}
	
	public EObject getOutput() {
		return output;
	}
	
	public EClassToEClass getMappingFor(String sourceName, String targetName) {		
		return elements.get(new ComplexKey(sourceName, targetName));
	}
	
	public String toString() {
		String s= "Metamodels: "+ 
			sourceMetamodel.getName() + " to " + 
			targetMetamodel.getName() + "\n";
		
		for(EClassToEClass each : elements.values())
			s+= each.toString() + "\n";
		return s;
	}
	
	private class ComplexKey { //please, refactor this shit.
		String sourceName;
		String targetName;
		
		public ComplexKey(EClassToEClass e) {
			this.sourceName = e.getSource().getName();
			this.targetName = e.getTarget().getName();
		}
		public ComplexKey(String sourceName, String targetName) {
			super();
			this.sourceName = sourceName;
			this.targetName = targetName;
		}
		public boolean equals(Object obj) {
			ComplexKey other= (ComplexKey) obj;
			return 
				this.sourceName.equals(other.sourceName) &&
				this.targetName.equals(other.targetName) ?
				true : false;
		}
		public int hashCode() {
			return (sourceName + targetName).hashCode();
		}
	}
}
