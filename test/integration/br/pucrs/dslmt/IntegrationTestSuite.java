package br.pucrs.dslmt;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import br.pucrs.dslmt.m2t.M2TTransformationProcessTest;
import br.pucrs.dslmt.t2m.T2MTransformationProcessesTest;
 
@RunWith(Suite.class)
@Suite.SuiteClasses({
	T2MTransformationProcessesTest.class,
	M2TTransformationProcessTest.class
})

public class IntegrationTestSuite {}