package br.pucrs.dslmt.m2t;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import br.pucrs.dslmt.Transformation;
import br.pucrs.dslmt.textualSpec.TextualSpec;
import br.pucrs.dslmt.util.TextFileIo;
import br.pucrs.dslmt.xmi.XmiReader;

public class M2TTransformation implements Transformation {
	private TextualSpec spec;
	private TemplateGenerator generator;
	private TemplateProcessor processor;
	private XmiReader reader;
	private TextFileIo io;
	private String inputPath;
	
	public M2TTransformation(TextualSpec spec) throws Exception {
		this.spec= spec;
		generator= new TemplateGenerator();
		processor= new TemplateProcessor();
		reader= new XmiReader();
		io= new TextFileIo();
		generateTemplates();
	}
	
	public M2TTransformation(TextualSpec spec, TemplateGenerator generator, TemplateProcessor processor, XmiReader reader, TextFileIo io) throws Exception {
		this.spec= spec;
		this.generator= generator;
		this.processor= processor;
		this.reader= reader;
		this.io= io;
		generateTemplates();
	}
	
	public void run(String inputPath) throws Exception {
		this.inputPath= inputPath;
		EObject model= readInput();
		String output= processTemplates(model);
		saveOutput(output);		
	}
	
	private void generateTemplates() throws Exception {
		generator.generate(spec);
	}
	
	private EObject readInput() throws Exception {
		EPackage metamodel= spec.getMetamodel();
		return reader.readModel(inputPath, metamodel);
	}
	
	private String processTemplates(EObject model) {
		processor.process(spec, model);
		return processor.getOutput();
	}
	
	private void saveOutput(String output) throws Exception {
		io.write(output, getOutputFileName());
	}
	
	public String getOutputFileName() {
		int i= inputPath.lastIndexOf('.');
		return inputPath.substring(0, i+1) + spec.getFileExtension();
	}
}
