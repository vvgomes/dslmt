package br.pucrs.dslmt.m2m;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;
import static org.easymock.classextension.EasyMock.replay;
import static org.junit.Assert.assertEquals;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.junit.Test;

import br.pucrs.dslmt.TestHelper;

public class MappingTest {
	TestHelper helper= new TestHelper();
	EPackage fsmMeta= helper.getFsmMetamodel();
	EPackage graphMeta= helper.getGraphMetamodel();
	EObject fsmModel= helper.getEvenOddZerosFsm();
	
	@Test
	public void shouldMapFsmModelToGraphModel() {		
		//fsm stuff...
		EClass fsm= (EClass) fsmMeta.getEClassifier("fsm");
		EReference states= (EReference) fsm.getEStructuralFeature("state");
		EClass state= (EClass) fsmMeta.getEClassifier("state");
		EAttribute id= (EAttribute) state.getEStructuralFeature("id");
		EReference transitions= (EReference) state.getEStructuralFeature("transition");
		EClass transition= (EClass) fsmMeta.getEClassifier("transition");
		EAttribute target= (EAttribute) transition.getEStructuralFeature("target");
		
		//graph stuff...
		EClass graph= (EClass) graphMeta.getEClassifier("graph");
		EReference nodes= (EReference) graph.getEStructuralFeature("node");
		EClass node= (EClass) graphMeta.getEClassifier("node");
		EAttribute label= (EAttribute) node.getEStructuralFeature("label");
		EReference edges= (EReference) node.getEStructuralFeature("edge");
		EClass edge= (EClass) graphMeta.getEClassifier("edge");
		EAttribute target2= (EAttribute) edge.getEStructuralFeature("target");
		
		Mapping mapping= new Mapping(fsmMeta, graphMeta);
		mapping
			.add(new EClassToEClass(fsm, graph, mapping).root()
				.add(new EReferenceToEReference(states, nodes, mapping)))
			.add(new EClassToEClass(state, node, mapping)
				.add(new EAttributeToEAttribute(id, label))
				.add(new EReferenceToEReference(transitions, edges, mapping)))
			.add(new EClassToEClass(transition, edge, mapping)
				.add(new EAttributeToEAttribute(target, target2)));
		
		mapping.map(fsmModel);
		EObject output= mapping.getOutput();
		
		String expected= "graph\nnode\nEven\nedge\nOdd\nedge\nEven\nnode\nOdd\nedge\nEven\nedge\nOdd\n";
		assertEquals(expected, new Walker().walk(output));
	}
	
	@Test
	public void shouldGiveMeTargetMetamodel() {
		Mapping mapping= new Mapping(fsmMeta, graphMeta);
		EPackage target= mapping.getTargetMetamodel();
		assertEquals("graph", target.getName());
	}
	
	@Test
	public void shouldGiveMeSourceMetamodel() {
		Mapping mapping= new Mapping(fsmMeta, graphMeta);
		EPackage source= mapping.getSourceMetamodel();
		assertEquals("fsm", source.getName());
	}
	
	@Test
	public void shouldStoreAndRetrieveElementsThisWay() {
		EClass fsm= (EClass) fsmMeta.getEClassifier("fsm");
		EClass graph= (EClass) graphMeta.getEClassifier("graph");
		
		EClassToEClass stored= createMock(EClassToEClass.class);
		expect(stored.getSource()).andReturn(fsm);
		expect(stored.getTarget()).andReturn(graph);
		replay(stored);
		
		Mapping mapping= new Mapping(fsmMeta, graphMeta);
		mapping.add(stored);
		EClassToEClass retrieved= mapping.getMappingFor("fsm", "graph");
		assertEquals(stored, retrieved);
	}
}
