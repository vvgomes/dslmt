package br.pucrs.dslmt.t2m;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;
import static org.easymock.classextension.EasyMock.replay;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import br.pucrs.dslmt.TestHelper;
import br.pucrs.dslmt.textualSpec.TextualSpec;

public class ParserGeneratorTest {
	TestHelper helper= new TestHelper();
	
	@Test
	public void shouldGenerateGrammar() throws Exception {
		deleteGrammarFile();
		
		TextualSpec spec= createMock(TextualSpec.class);		
		expect(spec.getGrammar()).andReturn(helper.getFsmGrammar());
		replay(spec);
		
		new ParserGenerator().generateGrammar(spec);
		assertTrue(new File("src/br/pucrs/dslmt/t2m/T.g").exists());
	}
	
	@Test
	public void shouldGenerateParserAndLexerFiles() throws Exception {
		deleteParserAndLexerFiles();
		
		new ParserGenerator().generateLexerAndParser();
		assertTrue(new File("src/br/pucrs/dslmt/t2m/TLexer.java").exists());
		assertTrue(new File("src/br/pucrs/dslmt/t2m/TParser.java").exists());
	}
	
	@Test
	public void shouldCompileParserAndLexer() throws Exception {
		deleteParserAndLexerClassFiles();
		
		new ParserGenerator().compileLexerAndParser();		
		assertTrue(new File("bin/br/pucrs/dslmt/t2m/TLexer.class").exists());
		assertTrue(new File("bin/br/pucrs/dslmt/t2m/TParser.class").exists());
	}
	
	private void deleteGrammarFile() {
		new File("src/br/pucrs/dslmt/t2m/T.g").delete();
	}
	
	private void deleteParserAndLexerFiles() {
		new File("src/br/pucrs/dslmt/t2m/TLexer.java").delete();
		new File("src/br/pucrs/dslmt/t2m/TParser.java").delete();
	}
	
	private void deleteParserAndLexerClassFiles() {
		new File("bin/br/pucrs/dslmt/t2m/TLexer.class").delete();
		new File("bin/br/pucrs/dslmt/t2m/TParser.class").delete();
	}
}
