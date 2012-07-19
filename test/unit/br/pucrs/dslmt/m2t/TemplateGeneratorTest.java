package br.pucrs.dslmt.m2t;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;
import static org.easymock.classextension.EasyMock.replay;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import br.pucrs.dslmt.TestHelper;
import br.pucrs.dslmt.textualSpec.TextualSpec;

public class TemplateGeneratorTest {
	
	@Test
	public void shouldGenerateTemplate() throws Exception {
		File fsmTemplate= new File("src/br/pucrs/dslmt/m2t/fsm.st");
		fsmTemplate.delete();
		
		TextualSpec spec= createMock(TextualSpec.class);		
		expect(spec.getTemplates()).andReturn(new TestHelper().getTemplates());
		replay(spec);
		
		new TemplateGenerator().generate(spec);
		assertTrue(fsmTemplate.exists());
	}
}