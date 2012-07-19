package br.pucrs.dslmt.m2m;

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

public class MappingReader extends DefaultHandler {
	private static final String ROOT = "model_to_model_mapping";
	private static final String ECLASS = "eclass-eclass";
	private static final String EATTRIBUTE = "eattribute-eattribute";
	private static final String EREFERENCE = "ereference-ereference";
	private static final String FINDER = "finder";
	private static final String PROPERTY = "property";
	
	private EPackage sourceMeta;
	private EPackage targetMeta;
	private Mapping mapping;
	private EClassToEClass eClassCache;
	private boolean rootMapping= true;
	private String sourceCache;
	private String targetCache;
	
	private enum State {IN_ECLASS, IN_EATTR, IN_EREF}
	private State state;
	
	public Mapping read(String path) 
	throws ParserConfigurationException, SAXException, IOException {
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser parser = spf.newSAXParser(); 
		parser.parse(new File(path), this);
		return mapping;
	}
	
	public void startElement(String uri, String localName, String tag, Attributes attrs) {
		if(tag.equalsIgnoreCase(ROOT)) {
			String sourcePath= attrs.getValue("source_metamodel");
			String targetPath= attrs.getValue("target_metamodel");
			try {
				XmiReader r= new XmiReader();
				sourceMeta= r.readMetamodel(sourcePath);
				targetMeta= r.readMetamodel(targetPath);
			}catch (Exception e) { e.printStackTrace();	}
			
			mapping= new Mapping(sourceMeta, targetMeta);
		}
		else if(tag.equalsIgnoreCase(ECLASS)) {
			state= State.IN_ECLASS;
		}
		else if(tag.equalsIgnoreCase(EATTRIBUTE)) {
			state= State.IN_EATTR;
		}
		else if(tag.equalsIgnoreCase(EREFERENCE)) {
			state= State.IN_EREF;
		}
		else if(tag.equalsIgnoreCase(FINDER)) {
			if(attrs.getValue("side").equals("left"))
				sourceCache= attrs.getValue("value");
			else {
				targetCache= attrs.getValue("value");				
				switch(state) {
				case IN_ECLASS: {
					EClass src= (EClass) sourceMeta.getEClassifier(sourceCache);
					EClass trg= (EClass) targetMeta.getEClassifier(targetCache);
					eClassCache= new EClassToEClass(src, trg, mapping);
					if(rootMapping) {
						eClassCache.root();
						rootMapping= false;
					}
					mapping.add(eClassCache);
					break;
				}
				case IN_EATTR: {
					EClass srcOwner= eClassCache.getSource();
					EClass trgOwner= eClassCache.getTarget();
					EAttribute src= (EAttribute) srcOwner.getEStructuralFeature(sourceCache);
					EAttribute trg= (EAttribute) trgOwner.getEStructuralFeature(targetCache);
					EAttributeToEAttribute e= new EAttributeToEAttribute(src, trg);
					eClassCache.add(e);
					break;
				}
				case IN_EREF: {
					EClass srcOwner= eClassCache.getSource();
					EClass trgOwner= eClassCache.getTarget();
					EReference src= (EReference) srcOwner.getEStructuralFeature(sourceCache);
					EReference trg= (EReference) trgOwner.getEStructuralFeature(targetCache);
					EReferenceToEReference e= new EReferenceToEReference(src, trg, mapping);
					eClassCache.add(e);
					break;
				}
				default:
					break;
				}
			}
		}
		else if(tag.equalsIgnoreCase(PROPERTY)) {
			
		}
	}
}
