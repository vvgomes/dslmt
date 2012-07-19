package br.pucrs.dslmt.t2m;

import br.pucrs.dslmt.textualSpec.TextualSpec;
import br.pucrs.dslmt.util.TextFileIo;

public class ParserGenerator {
	private static String GRAMMAR= "src/br/pucrs/dslmt/t2m/T.g";
	private static String PARSER= "src/br/pucrs/dslmt/t2m/TParser.java";
	private static String LEXER= "src/br/pucrs/dslmt/t2m/TLexer.java";
	private static String BINDIR= "bin";
	private static String ANTLR= "org.antlr.Tool";
	private static String OPTIONS= "-o src/br/pucrs/dslmt/t2m/";
	private static String SEP= System.getProperty("path.separator");
	private static String CLASSPATH= 
		"./lib/antlr-3.1.3.jar" + SEP +
		"./lib/antlr-2.7.7.jar" + SEP +
		"./lib/stringtemplate-3.2.jar" + SEP +
		"./lib/emf.common_2.1.0.jar" + SEP +
		"./lib/emf.commonj.sdo_2.1.0.jar" + SEP +
		"./lib/emf.ecore_2.1.0.jar" + SEP +
		"./lib/emf.ecore.change_2.1.0.jar" + SEP +
		"./lib/emf.ecore.change.edit_2.1.0.jar" + SEP +
		"./lib/emf.ecore.edit_2.1.1.jar" + SEP +
		"./lib/emf.ecore.sdo_2.1.1.jar" + SEP +
		"./lib/emf.ecore.sdo.edit_2.1.0.jar" + SEP +
		"./lib/emf.ecore.xmi_2.1.0.jar" + SEP +
		"./lib/emf.edit_2.1.1.jar" + SEP +
		"./lib/emf.mapping.ecore2xml_2.1.0" + SEP +
		"./lib/xsd_2.1.1" + SEP +
		"./lib/xsd.edit_2.1.0.jar";
	
	private TextFileIo io= new TextFileIo();
		
	public void generateGrammar(TextualSpec spec) throws Exception {
		io.write(spec.getGrammar(), GRAMMAR);
	}
	
	public void generateLexerAndParser() throws Exception {
		String cmd= onUnix() ? 
			"java -cp "+CLASSPATH+" "+ANTLR+" "+GRAMMAR : 
			"java -cp "+CLASSPATH+" "+ANTLR+" "+OPTIONS+" "+GRAMMAR;
		Process proc = Runtime.getRuntime().exec(cmd);
		if(proc.waitFor() != 0)
			throw new RuntimeException("Error generating parser:"+proc.exitValue());
		addSuppressWarningsToParser();
	}
	
	public void compileLexerAndParser() {
		String[] options= {"-classpath", CLASSPATH, "-d", BINDIR, LEXER, PARSER};
		com.sun.tools.javac.Main.compile(options);
	}
	
	private void addSuppressWarningsToParser() throws Exception {
		String content= io.read(PARSER);		
		
		int i= content.indexOf("public");
		content= 
			content.substring(0, i)+
			"@SuppressWarnings(\"unchecked\")"+
			content.substring(i, content.length());
		
		io.write(content, PARSER);
	}
	
	private boolean onUnix() {
		if(System.getProperty("os.name").contains("Windows"))
			return false;
		return true;
	}
}
