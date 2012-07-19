package br.pucrs.dslmt.m2m;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.verify;
import static org.easymock.classextension.EasyMock.createMock;
import static org.easymock.classextension.EasyMock.replay;
import static org.junit.Assert.assertEquals;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.junit.Test;

import br.pucrs.dslmt.TestHelper;

public class EClassToEClassTest {
	TestHelper helper= new TestHelper();	
	EPackage fsmMeta= helper.getFsmMetamodel();
	EPackage graphMeta= helper.getGraphMetamodel();
	
	@Test
	public void shouldMapStateToNode() {	
		EClass state= (EClass) fsmMeta.getEClassifier("state");
		EClass node= (EClass) graphMeta.getEClassifier("node");
		EObject input= getEvenState();
		EObject output= getGraphInstance();
		
		Mapping mapping= createMock(Mapping.class);
		expect(mapping.getTargetMetamodel()).andReturn(graphMeta);
		replay(mapping);
		
		MappingElement e= new EClassToEClass(state, node, mapping);
		e.map(input, output);
		 
		EList nodes= (EList) output.eGet(output.eClass().getEStructuralFeature("node"));
		assertEquals(1, nodes.size());
	}
	
	@Test
	public void shouldMapToRootTargetEClass() {
		EClass fsm= (EClass) fsmMeta.getEClassifier("fsm");
		EClass graph= (EClass) graphMeta.getEClassifier("graph");
		EObject input= helper.getEvenOddZerosFsm();
		EObject output= getGraphInstance();
		
		Mapping mapping= createMock(Mapping.class);
		expect(mapping.getTargetMetamodel()).andReturn(graphMeta);
		mapping.setOutputRoot(isA(EObject.class));
		replay(mapping);
		
		MappingElement e= new EClassToEClass(fsm, graph, mapping).root();
		e.map(input, output);
		verify();
	}
	
	@Test
	public void shouldMapStateToNodeWithSubmappings() {
		EClass state= (EClass) fsmMeta.getEClassifier("state");
		EClass node= (EClass) graphMeta.getEClassifier("node");
		EObject input= getEvenState();
		EObject output= getGraphInstance();
		
		Mapping mapping= createMock(Mapping.class);
		expect(mapping.getTargetMetamodel()).andReturn(graphMeta);
		
		MappingElement subElement= createMock(MappingElement.class);
		subElement.map(isA(EObject.class), isA(EObject.class));
		
		replay(mapping, subElement);
		
		new EClassToEClass(state, node, mapping)
			.add(subElement)
			.map(input, output);
		verify();
	}
	
	@Test
	public void shouldGiveMeSource() {
		EClass state= (EClass) fsmMeta.getEClassifier("state");
		EClass node= (EClass) graphMeta.getEClassifier("node");
		EClassToEClass e= new EClassToEClass(state, node, null);
		EClass source= e.getSource();
		assertEquals("state", source.getName());
	}
	
	@Test
	public void shouldGiveMeTarget() {
		EClass state= (EClass) fsmMeta.getEClassifier("state");
		EClass node= (EClass) graphMeta.getEClassifier("node");
		EClassToEClass e= new EClassToEClass(state, node, null);
		EClass target= e.getTarget();
		assertEquals("node", target.getName());
	}
	
	@Test
	public void toStringShouldBehaveThisWay() {
		EClass state= (EClass) fsmMeta.getEClassifier("state");
		EClass node= (EClass) graphMeta.getEClassifier("node");
		EClassToEClass e= new EClassToEClass(state, node, null);
		assertEquals("EClass: state to node", e.toString());
	}
	
	private EObject getEvenState() {
		EObject model= helper.getEvenOddZerosFsm(); 
		EClass fsm= model.eClass();
		EStructuralFeature f= fsm.getEStructuralFeature("state");
		EList states= (EList) model.eGet(f);
		return (EObject) states.get(0);
	}
	
	private EObject getGraphInstance() {
		EClass graph= (EClass) graphMeta.getEClassifier("graph");
		EFactory factory = graphMeta.getEFactoryInstance();		
		return factory.create(graph);
	}
}
