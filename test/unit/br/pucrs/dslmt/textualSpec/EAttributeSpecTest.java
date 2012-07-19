package br.pucrs.dslmt.textualSpec;

import static org.junit.Assert.assertEquals;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.junit.Test;

import br.pucrs.dslmt.TestHelper;
import br.pucrs.dslmt.textualSpec.EAttributeSpec;

public class EAttributeSpecTest {
	TestHelper helper= new TestHelper();
	
	@Test
	public void shouldGenerateGrammarForStringAttr() {		
		EClass fsm=(EClass)helper.getFsmMetamodel().getEClassifier("fsm"); 
		EAttribute inputAlphabet=(EAttribute)fsm.getEStructuralFeature("inputAlphabet");
		
		EAttributeSpec spec= new EAttributeSpec(inputAlphabet);
		
		String expected=
			"inputAlphabet=STRING "+
			"{$node.eSet(eClass.getEStructuralFeature(\"inputAlphabet\"), "+
			"removeDoubleQuotes($inputAlphabet.text));}";
		
		assertEquals(expected, spec.getGrammarFragment());
	}
	
	@Test
	public void shouldGenerateGrammarForCharAttr() {
		EClass transition=(EClass)helper.getFsmMetamodel().getEClassifier("transition"); 
		EAttribute inputt=(EAttribute)transition.getEStructuralFeature("inputt");
		
		EAttributeSpec spec= new EAttributeSpec(inputt);
		
		String expected=
			"inputt=CHAR "+
			"{$node.eSet(eClass.getEStructuralFeature(\"inputt\"), "+
			"removeQuotes($inputt.text));}";
		
		assertEquals(expected, spec.getGrammarFragment());
	}
	
	@Test
	public void shouldGenerateGrammarForStringAttrAsId() {		
		EClass transition=(EClass)helper.getFsmMetamodel().getEClassifier("transition"); 
		EAttribute target=(EAttribute)transition.getEStructuralFeature("target");
		
		EAttributeSpec spec= new EAttributeSpec(target).asId();
		
		String expected=
			"target=ID "+
			"{$node.eSet(eClass.getEStructuralFeature(\"target\"), "+
			"$target.text);}";
		
		assertEquals(expected, spec.getGrammarFragment());
	}
	
	@Test
	public void shouldGenerateTemplateForStringAttr() {
		EClass fsm=(EClass)helper.getFsmMetamodel().getEClassifier("fsm"); 
		EAttribute inputAlphabet=(EAttribute)fsm.getEStructuralFeature("inputAlphabet");
		
		EAttributeSpec spec= new EAttributeSpec(inputAlphabet);
		assertEquals("\"$inputAlphabet$\"", spec.getTemplateFragment());
	}
	
	@Test
	public void shouldGenerateTemplateForCharAttr() {
		EClass transition=(EClass)helper.getFsmMetamodel().getEClassifier("transition"); 
		EAttribute inputt=(EAttribute)transition.getEStructuralFeature("inputt");
		
		EAttributeSpec spec= new EAttributeSpec(inputt);
		assertEquals("'$inputt$'", spec.getTemplateFragment());
	}
	
	@Test
	public void shouldGenerateTemplateForStringAttrAsId() {		
		EClass transition=(EClass)helper.getFsmMetamodel().getEClassifier("transition"); 
		EAttribute target=(EAttribute)transition.getEStructuralFeature("target");
		
		EAttributeSpec spec= new EAttributeSpec(target).asId();		
		assertEquals("$target$", spec.getTemplateFragment());
	}
	
	@Test
	public void shouldGiveMeEAttributeName() {
		EClass transition=(EClass)helper.getFsmMetamodel().getEClassifier("transition"); 
		EAttribute target=(EAttribute)transition.getEStructuralFeature("target");
		
		EAttributeSpec spec= new EAttributeSpec(target);		
		assertEquals("target", spec.getEAttributeName());
	}
}