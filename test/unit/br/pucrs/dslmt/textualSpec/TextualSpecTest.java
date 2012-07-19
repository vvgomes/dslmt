package br.pucrs.dslmt.textualSpec;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;
import static org.easymock.classextension.EasyMock.replay;
import static org.junit.Assert.assertEquals;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.junit.Test;

import br.pucrs.dslmt.TestHelper;
import br.pucrs.dslmt.textualSpec.EClassSpec;

public class TextualSpecTest {	
	TestHelper helper= new TestHelper();
	EPackage metamodel= helper.getFsmMetamodel();
	
	@Test
	public void shouldGenerateBasicGrammar() throws Exception {
		TextualSpec spec= new TextualSpec(metamodel, "fsm");
		assertEquals(TextualSpec.BASIC_GRAMMAR, spec.getGrammar());
	}
	
	@Test
	public void shouldGenerateGrammarWithRules() {				
		EClass transition= (EClass) metamodel.getEClassifier("transition");
		String transitionGrammar= helper.getTransitionGrammar();
		
		EClassSpec transitionSpec= createMock(EClassSpec.class);
		expect(transitionSpec.getEClass()).andReturn(transition);
		expect(transitionSpec.getGrammarRule()).andReturn(transitionGrammar);
		replay(transitionSpec);
		
		TextualSpec spec= new TextualSpec(metamodel, "fsm")
			.add(transitionSpec);
		
		String expected= TextualSpec.BASIC_GRAMMAR + "\n" + transitionGrammar;
		assertEquals(expected, spec.getGrammar());
	}
	
	@Test
	public void shouldGiveMeRootEClassName() {
		TextualSpec spec= new TextualSpec(metamodel, "fsm");
		assertEquals("fsm", spec.getRootEClassName());
	}
	
	@Test
	public void shouldGiveMeTemplates() throws Exception {
		EClass fsm= (EClass) metamodel.getEClassifier("fsm");
		String fsmTemplate= helper.getTemplates().get("fsm");
		
		EClassSpec transitionSpec= createMock(EClassSpec.class);
		expect(transitionSpec.getEClass()).andReturn(fsm);
		expect(transitionSpec.getTemplate()).andReturn(fsmTemplate);
		replay(transitionSpec);
		
		TextualSpec spec= new TextualSpec(metamodel, "fsm")
			.add(transitionSpec);
		
		assertEquals(fsmTemplate, spec.getTemplates().get("fsm"));
	}
	
	@Test
	public void shouldGiveMeSpecForAnEClass() {
		EClass fsm= (EClass) metamodel.getEClassifier("fsm");
		
		EClassSpec fsmSpec= createMock(EClassSpec.class);
		expect(fsmSpec.getEClass()).andReturn(fsm);
		replay(fsmSpec);
		
		TextualSpec spec= new TextualSpec(metamodel, "fsm");
		spec.add(fsmSpec);
		
		assertEquals(fsmSpec, spec.getSpecFor("fsm"));
	}
	
	@Test
	public void shouldGiveMeDslFileExtension() {
		TextualSpec spec= new TextualSpec(metamodel, "fsm");
		assertEquals("fsm", spec.getFileExtension());
	}
}