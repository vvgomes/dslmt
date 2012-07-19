package br.pucrs.dslmt.textualSpec;
import static org.junit.Assert.*;

import org.junit.Test;

public class SpecReaderTest {
	
	@Test //reader need refactoring
	public void shouldReadSpecFileAndGenerateSpecObject() throws Exception {		
		TextualSpec spec= new SpecReader().read("models/fsm_t2m.xml");
		assertEquals("fsm", spec.getRootEClassName());
	}
}


