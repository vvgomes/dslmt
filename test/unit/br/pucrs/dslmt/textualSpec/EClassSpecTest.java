package br.pucrs.dslmt.textualSpec;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;

import org.eclipse.emf.ecore.EClass;
import org.junit.Test;

import br.pucrs.dslmt.TestHelper;
import br.pucrs.dslmt.textualSpec.EClassSpec;
import br.pucrs.dslmt.textualSpec.SpecElement;

public class EClassSpecTest {
	TestHelper helper= new TestHelper();
	EClass transition= (EClass)helper.getFsmMetamodel().getEClassifier("transition");
	
	@Test
	public void shouldGenerateBasicGrammarRule() {
		EClassSpec spec= new EClassSpec(transition);
		String expected= 
			"transition returns [EObject node]\n"+
			"\t@init {\n"+
			"\tEClass eClass= (EClass)pkg.getEClassifier(\"transition\");\n"+
			"\t$node= pkg.getEFactoryInstance().create(eClass);\n"+
			"\t}\n"+
			"\t:\n"+
			"\t;";
		assertEquals(expected, spec.getGrammarRule());
	}
	
	@Test
	public void shouldGenerateGrammarWithElements() {
		SpecElement transitionConst= createMock(SpecElement.class);
		expect(transitionConst.getGrammarFragment()).andReturn("'transition'");
		
		SpecElement inputtAttr= createMock(SpecElement.class);
		expect(inputtAttr.getGrammarFragment())
		.andReturn("inputt=CHAR {$node.eSet(eClass.getEStructuralFeature(\"inputt\"), removeQuotes($inputt.text));}");
		
		SpecElement slashConst= createMock(SpecElement.class);
		expect(slashConst.getGrammarFragment()).andReturn("'/'");
		
		SpecElement outputAttr= createMock(SpecElement.class);
		expect(outputAttr.getGrammarFragment())
		.andReturn("output=CHAR {$node.eSet(eClass.getEStructuralFeature(\"output\"), removeQuotes($output.text));}");
		
		SpecElement arrowConst= createMock(SpecElement.class);
		expect(arrowConst.getGrammarFragment())	.andReturn("'->'");
		
		SpecElement targetAttr= createMock(SpecElement.class);
		expect(targetAttr.getGrammarFragment())
		.andReturn("target=ID {$node.eSet(eClass.getEStructuralFeature(\"target\"), $target.text);}");
		
		replay(transitionConst, inputtAttr, slashConst, outputAttr, arrowConst, targetAttr);
		
		EClassSpec spec= new EClassSpec(transition)
			.add(transitionConst)
			.add(inputtAttr)
			.add(slashConst)
			.add(outputAttr)
			.add(arrowConst)
			.add(targetAttr);
		
		String expected= helper.getTransitionGrammar();
		
		assertEquals(expected, spec.getGrammarRule());
	}
	
	@Test
	public void shouldGiveMeEClass() {
		EClassSpec spec= new EClassSpec(transition);
		assertEquals(transition, spec.getEClass());
	}
	
	@Test
	public void shouldGenerateTemplateWithElements() throws Exception {
		SpecElement transitionConst= createMock(SpecElement.class);
		expect(transitionConst.getTemplateFragment()).andReturn("transition");
		
		SpecElement inputtAttr= createMock(SpecElement.class);
		expect(inputtAttr.getTemplateFragment()).andReturn("'$inputt$'");
		
		SpecElement slashConst= createMock(SpecElement.class);
		expect(slashConst.getTemplateFragment()).andReturn("/");
		
		SpecElement outputAttr= createMock(SpecElement.class);
		expect(outputAttr.getTemplateFragment()).andReturn("'$output$'");
		
		SpecElement arrowConst= createMock(SpecElement.class);
		expect(arrowConst.getTemplateFragment()).andReturn("->");
		
		SpecElement targetAttr= createMock(SpecElement.class);
		expect(targetAttr.getTemplateFragment()).andReturn("$target$");
		
		SpecElement space= createMock(SpecElement.class);
		expect(space.getTemplateFragment()).andReturn(" ").times(3);
		
		replay(transitionConst, inputtAttr, slashConst, outputAttr, arrowConst, targetAttr, space);
		
		EClassSpec spec= new EClassSpec(transition)
			.add(transitionConst)
			.add(space)
			.add(inputtAttr)
			.add(slashConst)
			.add(outputAttr)
			.add(space)
			.add(arrowConst)
			.add(space)
			.add(targetAttr);
		
		String expected= helper.getTemplates().get("transition");
		assertEquals(expected, spec.getTemplate());
	}
	
	@Test
	public void shouldGiveMeSpecElements() {
		SpecElement element= createMock(SpecElement.class);
		
		EClassSpec spec= new EClassSpec(transition)
			.add(element);
		
		assertEquals(element, spec.getElements().get(0));
	}
}