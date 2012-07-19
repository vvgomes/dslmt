package br.pucrs.dslmt.t2m;

import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import static org.easymock.EasyMock.verify;
import static org.easymock.classextension.EasyMock.createMock;
import static org.easymock.classextension.EasyMock.replay;
import static org.junit.Assert.assertEquals;

import org.eclipse.emf.ecore.EObject;
import org.junit.Before;
import org.junit.Test;

import br.pucrs.dslmt.TestHelper;
import br.pucrs.dslmt.Transformation;
import br.pucrs.dslmt.textualSpec.TextualSpec;
import br.pucrs.dslmt.xmi.XmiWriter;

public class T2MTransformationTest {
	TestHelper helper= new TestHelper();
	TextualSpec spec;
	ParserGenerator gen;
	XmiWriter writer;
	
	@Before
	public void setup() throws Exception {
		spec= createMock(TextualSpec.class);
		expect(spec.getGrammar()).andReturn(helper.getFsmGrammar());
		expect(spec.getMetamodel()).andReturn(helper.getFsmMetamodel());
		expect(spec.getRootEClassName()).andReturn("fsm");
		
		gen= createMock(ParserGenerator.class);
		gen.generateGrammar(spec);
		gen.generateLexerAndParser();
		gen.compileLexerAndParser();
		
		writer= createMock(XmiWriter.class);
		writer.saveModel(isA(EObject.class), eq("models/fsm_model.xmi"));
		
		replay(spec, gen, writer);
	}
	
	@Test
	public void shouldTransformTextModelIntoXmiModel() throws Exception {		
		Transformation t= new T2MTransformation(spec, gen, writer);
		t.run("models/fsm_model.fsm");
		verify();
	}
	
	@Test
	public void shouldGiveMeOutputFileName() throws Exception {
		Transformation t= new T2MTransformation(spec, gen, writer);
		t.run("models/fsm_model.fsm");
		assertEquals("models/fsm_model.xmi", t.getOutputFileName());
	}
}
