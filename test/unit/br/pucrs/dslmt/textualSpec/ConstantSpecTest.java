package br.pucrs.dslmt.textualSpec;

import static org.junit.Assert.*;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EcoreFactory;
import org.junit.Test;

import br.pucrs.dslmt.textualSpec.ConstantSpec;

public class ConstantSpecTest {
	
	@Test
	public void shouldGenerateGrammarLiteral() {
		ConstantSpec c= new ConstantSpec("transition");
		assertEquals("'transition'", c.getGrammarFragment());
	}
	
	@Test
	public void shouldGenerateOptionalGrammarLiteral() {
		ConstantSpec c= new ConstantSpec("start").optional();
		assertEquals("('start')?", c.getGrammarFragment());
	}
	
	@Test
	public void shouldGenerateOptionalGrammarLiteralAssociatedWithBooleanAttr() {
		ConstantSpec c= new ConstantSpec("start").optional().associatedWith(startAttr());
		
		String expected= 
			"('start' {$node.eSet(eClass.getEStructuralFeature(\"start\"), true);})?";
		
		assertEquals(expected, c.getGrammarFragment());
	}
	
	@Test
	public void shouldGenerateTemplate() {
		ConstantSpec c= new ConstantSpec("inputAlphabet");		
		assertEquals("inputAlphabet", c.getTemplateFragment());
	}
	
	@Test
	public void shouldGenerateOptionalTemplateAssociatedWithBooleanAttr() {
		ConstantSpec c= new ConstantSpec("start").optional().associatedWith(startAttr());
		assertEquals("$if(start)$start$endif$", c.getTemplateFragment());
	}
	
	@Test
	public void shouldGenerateNewLineForTemplateProperly() {
		ConstantSpec c= new ConstantSpec("\\n");
		assertEquals('\n', c.getTemplateFragment().charAt(0));
	}
	
	@Test
	public void shouldGenerateTabForTemplateProperly() {
		ConstantSpec c= new ConstantSpec("\\t");
		assertEquals('\t', c.getTemplateFragment().charAt(0));
	}
	
	@Test
	public void shouldGiveMeTheAssociatedAttr() {
		EAttribute start= startAttr();
		ConstantSpec c= new ConstantSpec("start").optional().associatedWith(start);
		assertEquals(start, c.getAssociatedAttr());
	}
	
	private EAttribute startAttr() {
		EAttribute start= EcoreFactory.eINSTANCE.createEAttribute();
		start.setName("start");
		return start;
	}
}