package br.pucrs.dslmt.textualSpec;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;

public class EAttributeSpec implements SpecElement {
	private static Map<String, String> tokenTypes= new HashMap<String, String>();
	static {
		tokenTypes.put("EString", "STRING");
		tokenTypes.put("EChar", "CHAR");
		tokenTypes.put("EBoolean", "BOOL");
		tokenTypes.put("EInt", "INT");
		tokenTypes.put("EFloat", "REAL");
		tokenTypes.put("EDouble", "REAL");
	}
	
	private EAttribute attr;
	private String tokenType;
	
	public EAttributeSpec(EAttribute attr) {
		this.attr= attr;
		tokenType= tokenTypes.get(attr.getEType().getName());
	}
	
	public EAttributeSpec asId() {
		tokenType= "ID";
		return this;
	}
	
	public String getGrammarFragment() {
		String name= attr.getName();
		String value= "$"+name+".text";
		
		if(tokenType.equals("CHAR"))
			value= "removeQuotes("+ value +")";
		else if(tokenType.equals("STRING"))
			value= "removeDoubleQuotes("+ value +")";
		
		return 
		name + "=" + tokenType +
		" {$node.eSet(eClass.getEStructuralFeature(\""+ name +"\"), "+ value +");}";
	}
	
	public String getTemplateFragment() {
		String name= attr.getName();
		String template= "$"+name+"$";
		
		if(tokenType.equals("CHAR"))
			template= "'"+ template +"'";
		else if(tokenType.equals("STRING"))
			template= "\""+ template +"\"";
		
		return template;
	}
	
	public String getEAttributeName() {
		return attr.getName();
	}
}
