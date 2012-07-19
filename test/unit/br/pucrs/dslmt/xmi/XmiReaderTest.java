package br.pucrs.dslmt.xmi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.junit.Test;

import br.pucrs.dslmt.TestHelper;

public class XmiReaderTest {
	
	@Test
	public void shouldReadFsmMetamodel() throws Exception {
		EPackage metamodel = new XmiReader().readMetamodel("models/fsm_metamodel.xmi");
		
		assertNotNull((EClass)metamodel.getEClassifier("fsm"));
		assertNotNull((EClass)metamodel.getEClassifier("state"));
		assertNotNull((EClass)metamodel.getEClassifier("transition"));
	}
	
	@Test 
	public void shouldReadFsmModelExample() throws Exception {
		EPackage metamodel= new TestHelper().getFsmMetamodel();
		EObject root= new XmiReader().readModel("models/fsm_model.xmi", metamodel);
		
		EClass fsm = root.eClass();
		assertEquals("01", root.eGet(fsm.getEStructuralFeature("inputAlphabet")));
	}
}
