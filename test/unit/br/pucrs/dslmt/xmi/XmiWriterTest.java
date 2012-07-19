package br.pucrs.dslmt.xmi;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.junit.After;
import org.junit.Test;

import br.pucrs.dslmt.TestHelper;

public class XmiWriterTest {
	TestHelper helper= new TestHelper();
	String temp= "models/temp.xmi";
	
	@After
	public void deleteTempFile() {
		new File(temp).delete();
	}
	
	@Test
	public void shouldSerializeMetamodel() throws Exception {
		EPackage metamodel = helper.getFsmMetamodel();
		new XmiWriter().saveMetamodel(metamodel, temp);
		assertTrue(helper.sameContent("models/fsm_metamodel.xmi", temp));
	}
	
	@Test
	public void shouldSerializeModel() throws Exception {
		EObject modelObject = helper.getFsmModel();
		new XmiWriter().saveModel(modelObject, temp);
		assertTrue(helper.sameContent("models/fsm_model.xmi", temp));
	}
	
	/*public static void main(String[] args) throws Exception {
		//new File("models/fsm-metamodel.xmi").delete();
		new File("models/even-odd-zeros.xmi").delete();
		TestHelper helper= new TestHelper();
		//EPackage metamodel= helper.getFsmMetamodel();
		EObject model= helper.getEvenOddZerosFsm();
		XmiWriter w= new XmiWriter();
		//w.saveMetamodel(metamodel, "models/fsm-metamodel.xmi");
		w.saveModel(model, "models/even-odd-zeros.xmi");
		System.out.println("ok");
	}*/
}
