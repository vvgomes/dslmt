package br.pucrs.dslmt.textualSpec;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import br.pucrs.dslmt.xmi.XmiReader;

public class SpecReader extends DefaultHandler {
	private static final String TEXTUAL_SPEC = "textual_specification";
	private static final String ECLASS = "eclass";
	private static final String EATTRIBUTE = "eattribute";
	private static final String EREFERENCE = "ereference";
	private static final String CONSTANT = "constant";
	
	private StringBuffer buffer = new StringBuffer(50);
	private TextualSpec spec;
	private EClassSpec eClassSpecCache;
	private Attributes attrsCache;
	
	public TextualSpec read(String pathToSpec) 
	throws ParserConfigurationException, SAXException, IOException {
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser parser = spf.newSAXParser(); 
		parser.parse(new File(pathToSpec), this);
		return spec;
	}
	
	public void startElement(String uri, String localName, String tag, Attributes attrs) {
		if(tag.equalsIgnoreCase(TEXTUAL_SPEC)) {
			String metamodelFile= attrs.getValue("metamodel");
			EPackage metamodel= null;
			try {
				metamodel= new XmiReader().readMetamodel(metamodelFile);
			}catch (Exception e) { e.printStackTrace();	}
			
			String fileExtension= attrs.getValue("file_extension");
			
			spec= new TextualSpec(metamodel, fileExtension);
		}
		else if(tag.equalsIgnoreCase(ECLASS)) {
			String eclassName= attrs.getValue("name");
			EClass eClass= (EClass)spec.getMetamodel().getEClassifier(eclassName);
			eClassSpecCache= new EClassSpec(eClass);
			spec.add(eClassSpecCache);
		}
		else if(tag.equalsIgnoreCase(EATTRIBUTE)) {
			String attrName= attrs.getValue("name");
			EClass eClass= eClassSpecCache.getEClass();
			EAttribute attr= (EAttribute) eClass.getEStructuralFeature(attrName);
			EAttributeSpec attrSpec= new EAttributeSpec(attr);
			if((attrs.getValue("filter") )!= null)
				attrSpec.asId();
			eClassSpecCache.add(attrSpec);
		}
		else if(tag.equalsIgnoreCase(EREFERENCE)) {
			String to= attrs.getValue("to");
			EClass eClass= eClassSpecCache.getEClass();
			EReference ref= (EReference)eClass.getEStructuralFeature(to);
			EReferenceSpec refSpec= new EReferenceSpec(ref);
			String separator= attrs.getValue("separator");
			if(separator != null)
				refSpec.withSeparator(separator);
			eClassSpecCache.add(refSpec);
		}
		else if(tag.equalsIgnoreCase(CONSTANT)) {
			attrsCache= attrs;
			buffer= new StringBuffer(50);
		}
	}
	
	public void endElement(String uri, String localName, String tag){
		if(tag.equalsIgnoreCase(CONSTANT)) {
			ConstantSpec cs= new ConstantSpec(buffer.toString());
			
			String optional= attrsCache.getValue("optional");
			if(optional != null && optional.equalsIgnoreCase("true"))
				cs.optional();
			
			String attrName= attrsCache.getValue("associated_with");
			if(attrName != null) {
				EClass eClass= eClassSpecCache.getEClass();
				EAttribute attr= (EAttribute) eClass.getEStructuralFeature(attrName);
				cs.associatedWith(attr);
			}
			
			eClassSpecCache.add(cs);
			buffer.delete(0, buffer.length());
		}
	}
	
	public void characters(char[] ch, int start, int length) {
		buffer.append(ch,start,length);
	}
}
