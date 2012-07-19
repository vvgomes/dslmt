package br.pucrs.dslmt.m2t;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.verify;
import static org.easymock.classextension.EasyMock.createMock;
import static org.easymock.classextension.EasyMock.replay;
import static org.junit.Assert.assertEquals;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.junit.Before;
import org.junit.Test;

import br.pucrs.dslmt.TestHelper;
import br.pucrs.dslmt.Transformation;
import br.pucrs.dslmt.textualSpec.TextualSpec;
import br.pucrs.dslmt.util.TextFileIo;
import br.pucrs.dslmt.xmi.XmiReader;

public class M2TTransformationTest {
	TestHelper helper= new TestHelper();
	TextualSpec spec;
	TemplateGenerator gen;
	TemplateProcessor pro;
	XmiReader reader;
	TextFileIo io;
	
	@Before
	public void setup() throws Exception {
		EPackage metamodel= helper.getFsmMetamodel();
		EObject model= helper.getFsmModel();
		String text= helper.getFsmText();
		
		spec= createMock(TextualSpec.class);
		expect(spec.getMetamodel()).andReturn(metamodel);
		expect(spec.getFileExtension()).andReturn("fsm").atLeastOnce();
		
		gen= createMock(TemplateGenerator.class);
		gen.generate(spec);
		
		pro= createMock(TemplateProcessor.class);
		pro.process(spec, model);
		expect(pro.getOutput()).andReturn(text);
		
		reader= createMock(XmiReader.class);
		expect(reader.readModel("models/fsm_model.xmi", metamodel)).andReturn(model);
		
		io= createMock(TextFileIo.class);
		io.write(text, "models/fsm_model.fsm");
		
		replay(spec, gen, pro, reader, io);
	}
	
	@Test
	public void shouldTransformTextModelIntoXmiModel() throws Exception {		
		Transformation t= new M2TTransformation(spec, gen, pro, reader, io);
		t.run("models/fsm_model.xmi");
		verify();
	}
	
	@Test
	public void shouldGiveMeOutputFileName() throws Exception {
		Transformation t= new M2TTransformation(spec, gen, pro, reader, io);
		t.run("models/fsm_model.xmi");
		assertEquals("models/fsm_model.fsm", t.getOutputFileName());
	}
}
