package br.pucrs.dslmt.m2m;

import org.junit.Test;
import static org.junit.Assert.*;

public class MappingReaderTest {

	@Test
	public void shouldReadMapping() throws Exception {
		String expected= 
			"Metamodels: fsm to graph\n" +
			"EClass: fsm to graph\n" +
			"\tEReference: state to node\n" +
			"EClass: state to node\n" +
			"\tEAttribute: id to label\n" +
			"\tEReference: transition to edge\n" +
			"EClass: transition to edge\n" +
			"\tEAttribute: target to target\n";
		
		Mapping m= new MappingReader().read("models/fsm-graph.xml");
		assertEquals(expected, m.toString());
	}
}
