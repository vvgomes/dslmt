package br.pucrs.dslmt.textualSpec;

import static org.junit.Assert.assertEquals;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.junit.Test;

import br.pucrs.dslmt.TestHelper;
import br.pucrs.dslmt.textualSpec.EReferenceSpec;

public class EReferenceSpecTest {
	EReference stateToTransition= (EReference)
		((EClass)new TestHelper().getFsmMetamodel().getEClassifier("state"))
			.getEStructuralFeature("transition");
	
	@Test
	public void shouldGenerateGrammarRuleReference() {
		EReferenceSpec spec= new EReferenceSpec(stateToTransition);
		String expected=
			"(r=transition {((EList)$node.eGet(eClass.getEStructuralFeature(\"transition\"))).add($r.node);})*";
		assertEquals(expected, spec.getGrammarFragment());
	}
	
	@Test
	public void shouldGenerateGrammarRuleReferenceWithSeparator() {
		EReferenceSpec spec= new EReferenceSpec(stateToTransition).withSeparator(",");
		String expected=
			"(r=transition {((EList)$node.eGet(eClass.getEStructuralFeature(\"transition\"))).add($r.node);})? " +
			"(',' r=transition {((EList)$node.eGet(eClass.getEStructuralFeature(\"transition\"))).add($r.node);})*";
		assertEquals(expected, spec.getGrammarFragment());
	}
	
	@Test
	public void shouldGenerateTemplateReference() {
		EReferenceSpec spec= new EReferenceSpec(stateToTransition);
		assertEquals("$transition; separator=\"\\n\"$", spec.getTemplateFragment());
	}
	
	@Test
	public void shouldGenerateTemplateReferenceWithSeparator() {
		EReferenceSpec spec= new EReferenceSpec(stateToTransition).withSeparator(",");
		assertEquals("$transition; separator=\",\"$", spec.getTemplateFragment());
	}
	
	@Test
	public void shouldGiveMeEReferenceName() {
		EReferenceSpec spec= new EReferenceSpec(stateToTransition);
		assertEquals("transition", spec.getEReferenceName());
	}
}
