package br.pucrs.dslmt;

import java.io.File;

import br.pucrs.dslmt.m2t.M2TTransformation;
import br.pucrs.dslmt.t2m.T2MTransformation;
import br.pucrs.dslmt.textualSpec.SpecReader;
import br.pucrs.dslmt.textualSpec.TextualSpec;


public class Dslmt {
	
	public static void main(String[] args) throws Exception {		
		new Dslmt().runCommandLineTool(args);
	}
	
	public void runCommandLineTool(String[] args) throws Exception {
		if(!validParameters(args)) {
			System.out.println("Invalid parameters.");
			System.out.println("Usage: -<transf_type> <textual_spec> <input_model>");
			return;
		}			
		
		String transfType= args[0];
		String specPath= args[1];
		String inputPath= args[2];
		
		if(!validFile(specPath)) {
			System.out.println(specPath + " not found.");
			return;
		}
		if(!validFile(inputPath)) {
			System.out.println(inputPath + " not found.");
			return;
		}
		
		TextualSpec spec= new SpecReader().read(specPath);
		Transformation t= getTransformation(transfType, spec);
		t.run(inputPath);
		System.out.println(t.getOutputFileName() + " generated successfully.");
	}
	
	private Transformation getTransformation(String arg, TextualSpec spec) throws Exception {
		if(arg.equals("-t2m"))
			return new T2MTransformation(spec);
		return new M2TTransformation(spec);
	}
	
	private boolean validParameters(String[] args) {
		if(args.length < 3)
			return false;
		if(!args[0].equals("-t2m") && !args[0].equals("-m2t"))
			return false;
		return true;
	}
	
	private boolean validFile(String path) {
		if(new File(path).exists())
			return true;
		return false;
	}
}
