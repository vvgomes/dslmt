package br.pucrs.dslmt.m2m;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import br.pucrs.dslmt.Transformation;

public class M2MTransformationTest {
	
	@Test
	public void shouldTransformFsmModelIntoGraphModel() throws Exception {
		String outputPath= "models/graph-model.xmi";
		File output= new File(outputPath);
		output.delete();
		
		Mapping m= new MappingReader().read("models/fsm-graph.xml");
		Transformation t= new M2MTransformation(m, outputPath);
		t.run("models/even-odd-zeros.xmi");
		assertTrue(output.exists());
	}
}