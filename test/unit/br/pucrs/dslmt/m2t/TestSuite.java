package br.pucrs.dslmt.m2t;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import br.pucrs.dslmt.m2t.TemplateGeneratorTest;
 
@RunWith(Suite.class)
@Suite.SuiteClasses({
	TemplateGeneratorTest.class,
	TemplateProcessorTest.class,
	M2TTransformationTest.class
})

public class TestSuite {}