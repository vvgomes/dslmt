package br.pucrs.dslmt.m2m;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
 
@RunWith(Suite.class)
@Suite.SuiteClasses({
	EAttributeToEAttributeTest.class,
	EReferenceToEReferenceTest.class,
	EClassToEClassTest.class,
	MappingTest.class,
	MappingReaderTest.class,
	M2MTransformationTest.class
	
})

public class TestSuite {}