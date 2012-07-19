package br.pucrs.dslmt.m2m;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.isA;
import static org.easymock.EasyMock.verify;
import static org.easymock.classextension.EasyMock.createMock;
import static org.easymock.classextension.EasyMock.replay;
import static org.junit.Assert.assertEquals;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.junit.Test;

import br.pucrs.dslmt.TestHelper;

public class EReferenceToEReferenceTest {
	TestHelper helper= new TestHelper();
	EPackage fsmMeta= helper.getFsmMetamodel();
	EPackage graphMeta= helper.getGraphMetamodel();
	
	@Test
	public void shouldMapStatesToNodes() {
		EReference states= getStateRef();		
		EReference nodes= getNodeRef();
		EObject input= helper.getEvenOddZerosFsm();
		EObject output= getGraphInstance();
		
		EClassToEClass stateToNode= createMock(EClassToEClass.class);
		stateToNode.map(isA(EObject.class), isA(EObject.class));
		expectLastCall().times(2);
		
		Mapping mapping= createMock(Mapping.class);
		expect(mapping.getMappingFor("state", "node")).andReturn(stateToNode);
		expectLastCall().times(2);
		
		replay(mapping, stateToNode);
		
		new EReferenceToEReference(states, nodes, mapping)
			.map(input, output);
		verify();
	}
	
	@Test
	public void toStringShouldBehaveThisWay() {
		EReference states= getStateRef();		
		EReference nodes= getNodeRef();
		MappingElement e= new EReferenceToEReference(states, nodes, null);
		assertEquals("EReference: state to transition", e.toString());
	}
	
	private EReference getStateRef() {
		EPackage fsmMeta= helper.getFsmMetamodel();
		EClass fsm= (EClass) fsmMeta.getEClassifier("fsm");
		EReference states= (EReference) fsm.getEStructuralFeature("state");
		return states;
	}
	
	private EReference getNodeRef() {
		EPackage graphMeta= helper.getGraphMetamodel();
		EClass graph= (EClass) graphMeta.getEClassifier("graph");
		EReference nodes= (EReference) graph.getEStructuralFeature("node");
		return nodes;
	}
	
	private EObject getGraphInstance() {
		EClass graph= (EClass) graphMeta.getEClassifier("graph");
		EFactory factory = graphMeta.getEFactoryInstance();		
		return factory.create(graph);
	}
}
