package br.pucrs.dslmt.m2m;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import br.pucrs.dslmt.Transformation;
import br.pucrs.dslmt.xmi.XmiReader;
import br.pucrs.dslmt.xmi.XmiWriter;

public class M2MTransformation implements Transformation {
	private Mapping mapping;
	private String outputPath;
	private XmiReader modelReader= new XmiReader();
	private XmiWriter modelWriter= new XmiWriter();
	
	public M2MTransformation(Mapping mapping, String outputPath) {
		this.mapping= mapping;
		this.outputPath= outputPath;
	}
	
	public void run(String inputPath) throws Exception {
		EObject inputModel= readInput(inputPath);
		mapping.map(inputModel);
		saveOutput();
	}
	
	private EObject readInput(String inputPath) throws Exception {
		EPackage metamodel= mapping.getSourceMetamodel();
		EObject inputModel= modelReader.readModel(inputPath, metamodel);
		return inputModel;
	}
	
	private void saveOutput() throws Exception {
		EObject outputModel= mapping.getOutput();
		modelWriter.saveModel(outputModel, outputPath);
	}
	
	public String getOutputFileName() {
		return outputPath;
	}
}