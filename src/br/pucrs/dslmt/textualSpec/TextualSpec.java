package br.pucrs.dslmt.textualSpec;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import br.pucrs.dslmt.util.TextFileIo;

public class TextualSpec {
	public static String BASIC_GRAMMAR;
	
	static {
		try {
			BASIC_GRAMMAR= new TextFileIo().read("resources/basic-grammar.txt");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private EPackage metamodel;
	private String fileExtension;
	private Map<String, EClassSpec> specs= new LinkedHashMap<String, EClassSpec>();
	
	public TextualSpec(EPackage metamodel, String fileExtension) {
		this.metamodel= metamodel;
		this.fileExtension= fileExtension;
	}
	
	public TextualSpec add(EClassSpec spec) {
		specs.put(spec.getEClass().getName(), spec);
		return this;
	}
	
	public EPackage getMetamodel() {
		return metamodel;
	}
	
	public String getGrammar() {
		String grammar= BASIC_GRAMMAR;
		for(EClassSpec each : specs.values())
			grammar+= "\n" + each.getGrammarRule();
		return grammar;
	}
	
	public String getRootEClassName() {
		return metamodel.getName();
	}
	
	public Map<String, String> getTemplates() {
		Map<String, String> templates= new HashMap<String, String>();
		for(String eClassName : specs.keySet()) {
			String template= specs.get(eClassName).getTemplate();
			templates.put(eClassName, template);
		}
		return templates;
	}
	
	public EClassSpec getSpecFor(String eClassName) {
		return specs.get(eClassName);
	}
	
	public String getFileExtension() {
		return fileExtension;
	}
}
