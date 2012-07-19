package br.pucrs.dslmt.m2m;

import static org.junit.Assert.assertEquals;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.junit.Test;

import br.pucrs.dslmt.TestHelper;

public class EAttributeToEAttributeTest {
	TestHelper helper= new TestHelper();
	
	@Test
	public void shouldMapIdToLabel() {
		EAttribute id= getIdAttr();		
		EAttribute label= getLabelAttr();
		EObject input= getEvenState();
		EObject output= createNodeInstance();
		
		MappingElement e= new EAttributeToEAttribute(id, label);
		e.map(input, output);
		
		String value= (String) output.eGet(output.eClass().getEStructuralFeature("label"));
		assertEquals("Even", value);
	}
	
	@Test
	public void toStringShouldBehaveThisWay() {
		EAttribute id= getIdAttr();		
		EAttribute label= getLabelAttr();
		MappingElement e= new EAttributeToEAttribute(id, label);
		assertEquals("EAttribute: id to label", e.toString());
	}
	
	private EAttribute getIdAttr() {
		EPackage fsmMeta= helper.getFsmMetamodel();
		EClass state= (EClass) fsmMeta.getEClassifier("state");
		EAttribute id= (EAttribute) state.getEStructuralFeature("id");
		return id;
	}
	
	private EAttribute getLabelAttr() {
		EPackage graphMeta= helper.getGraphMetamodel();
		EClass node= (EClass) graphMeta.getEClassifier("node");
		EAttribute label= (EAttribute) node.getEStructuralFeature("label");
		return label;
	}
	
	private EObject getEvenState() {
		EObject model= helper.getEvenOddZerosFsm(); 
		EClass fsm= model.eClass();
		EStructuralFeature f= fsm.getEStructuralFeature("state");
		EList states= (EList) model.eGet(f);
		return (EObject) states.get(0);
	}
	
	private EObject createNodeInstance() {
		EPackage graphMeta= helper.getGraphMetamodel();
		EClass node= (EClass) graphMeta.getEClassifier("node");
		EFactory factory = graphMeta.getEFactoryInstance();		
		return factory.create(node);
	}
}
