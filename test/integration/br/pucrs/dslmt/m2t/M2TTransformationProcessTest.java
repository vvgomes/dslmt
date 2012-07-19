package br.pucrs.dslmt.m2t;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import br.pucrs.dslmt.Transformation;
import br.pucrs.dslmt.textualSpec.SpecReader;
import br.pucrs.dslmt.textualSpec.TextualSpec;

public class M2TTransformationProcessTest {
	
	@Test
	public void shouldRunM2TTransformation() throws Exception {
		File evenOddZerosFsm= new File("models/fsm_model.fsm");
		evenOddZerosFsm.delete();
		
		TextualSpec spec= new SpecReader().read("models/fsm_m2t.xml");
		Transformation t= new M2TTransformation(spec);
		t.run("models/fsm_model.xmi");		
		assertTrue(evenOddZerosFsm.exists());
	}
}
