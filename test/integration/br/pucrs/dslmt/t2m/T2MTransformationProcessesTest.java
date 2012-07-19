package br.pucrs.dslmt.t2m;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import br.pucrs.dslmt.Transformation;
import br.pucrs.dslmt.textualSpec.SpecReader;
import br.pucrs.dslmt.textualSpec.TextualSpec;

public class T2MTransformationProcessesTest {
	
	@Test
	public void shouldRunT2MTransformation() throws Exception {
		File evenOddZerosXmi= new File("models/fsm_model.xmi");
		evenOddZerosXmi.delete();
		
		TextualSpec spec= new SpecReader().read("models/fsm_t2m.xml");	
		Transformation t= new T2MTransformation(spec);
		t.run("models/fsm_model.fsm");
		assertTrue(evenOddZerosXmi.exists());
	}
}
