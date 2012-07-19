package br.pucrs.dslmt;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
 
@RunWith(Suite.class)
@Suite.SuiteClasses({
	br.pucrs.dslmt.xmi.TestSuite.class,
	br.pucrs.dslmt.textualSpec.TestSuite.class,
	br.pucrs.dslmt.t2m.TestSuite.class,
	br.pucrs.dslmt.m2t.TestSuite.class
})

public class UnitTestSuite {}