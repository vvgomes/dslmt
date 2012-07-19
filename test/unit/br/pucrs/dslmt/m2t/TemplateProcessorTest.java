package br.pucrs.dslmt.m2t;

import static java.util.Arrays.asList;
import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;
import static org.easymock.classextension.EasyMock.replay;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.junit.Test;

import br.pucrs.dslmt.TestHelper;
import br.pucrs.dslmt.textualSpec.ConstantSpec;
import br.pucrs.dslmt.textualSpec.EAttributeSpec;
import br.pucrs.dslmt.textualSpec.EClassSpec;
import br.pucrs.dslmt.textualSpec.SpecElement;
import br.pucrs.dslmt.textualSpec.TextualSpec;

public class TemplateProcessorTest {
	
	@Test
	public void shouldProcessFsmTemplates() throws Exception {
		EObject model= new TestHelper().getFsmModel();
		TextualSpec spec = mockSpec();
		String expected="inputAlphabet \"01\"\noutputAlphabet \"eo\"\n";
		
		TemplateProcessor processor= new TemplateProcessor();
		processor.process(spec, model);
		
		assertEquals(expected, processor.getOutput());
	}
	
	private TextualSpec mockSpec() {		
		ConstantSpec cons= createMock(ConstantSpec.class);
		expect(cons.getAssociatedAttr()).andReturn(null);
		
		EAttributeSpec inpAlpSpec= createMock(EAttributeSpec.class);
		expect(inpAlpSpec.getEAttributeName()).andReturn("inputAlphabet");
		
		EAttributeSpec outAlpSpec= createMock(EAttributeSpec.class);
		expect(outAlpSpec.getEAttributeName()).andReturn("outputAlphabet");
		
		List<SpecElement> fsmElements= asList(cons, inpAlpSpec, outAlpSpec);
		
		EClassSpec fsmSpec= createMock(EClassSpec.class);
		expect(fsmSpec.getElements()).andReturn(fsmElements);
		
		TextualSpec spec= createMock(TextualSpec.class);
		expect(spec.getRootEClassName()).andReturn("fsm");
		expect(spec.getSpecFor("fsm")).andReturn(fsmSpec);
		
		replay(spec, fsmSpec, cons, inpAlpSpec, outAlpSpec);
		return spec;
	}
}
