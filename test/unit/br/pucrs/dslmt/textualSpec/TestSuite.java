package br.pucrs.dslmt.textualSpec;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import br.pucrs.dslmt.textualSpec.ConstantSpecTest;
import br.pucrs.dslmt.textualSpec.EAttributeSpecTest;
import br.pucrs.dslmt.textualSpec.EClassSpecTest;
import br.pucrs.dslmt.textualSpec.EReferenceSpecTest;
import br.pucrs.dslmt.textualSpec.SpecReaderTest;
import br.pucrs.dslmt.textualSpec.TextualSpecTest;
 
@RunWith(Suite.class)
@Suite.SuiteClasses({
	ConstantSpecTest.class,
	EAttributeSpecTest.class,
	EClassSpecTest.class,
	EReferenceSpecTest.class,
	SpecReaderTest.class,
	TextualSpecTest.class
})

public class TestSuite {}