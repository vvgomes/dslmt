package br.pucrs.dslmt.t2m;

import java.io.FileInputStream;
import java.lang.reflect.Method;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.eclipse.emf.ecore.EObject;

import br.pucrs.dslmt.Transformation;
import br.pucrs.dslmt.textualSpec.TextualSpec;
import br.pucrs.dslmt.xmi.XmiWriter;

public class T2MTransformation implements Transformation {
	private TextualSpec spec;
	private ParserGenerator generator;
	private XmiWriter writer;
	private String inputPath;
	
	public T2MTransformation(TextualSpec spec) throws Exception {
		this.spec= spec;
		generator= new ParserGenerator();
		writer= new XmiWriter(); 
		generateParser();
	}
	
	public T2MTransformation(TextualSpec spec, ParserGenerator generator, XmiWriter writer) throws Exception {
		this.spec= spec;
		this.generator= generator;
		this.writer= writer;
		generateParser();
	}
	
	public void run(String inputPath) throws Exception {
		this.inputPath= inputPath;
		EObject model= parseInput();
		saveOutput(model);		
	}
	
	private void generateParser() throws Exception {
		generator.generateGrammar(spec);
		generator.generateLexerAndParser();
		generator.compileLexerAndParser();
	}
	
	private EObject parseInput() throws Exception {
		ANTLRInputStream input= new ANTLRInputStream(new FileInputStream(inputPath));
		TLexer lexer= new TLexer(input);
		CommonTokenStream tokens= new CommonTokenStream(lexer);
		TParser parser= new TParser(tokens, spec.getMetamodel());		
		Method m= parser.getClass().getMethod(spec.getRootEClassName());
		return (EObject) m.invoke(parser);
	}
	
	private void saveOutput(EObject model) throws Exception {
		writer.saveModel(model, getOutputFileName());
	}
	
	public String getOutputFileName() {
		int i= inputPath.lastIndexOf('.');
		return inputPath.substring(0, i+1) + "xmi";
	}
}
