package br.pucrs.dslmt.xmi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

@SuppressWarnings("unchecked")

public class XmiWriter {
	
	public void saveMetamodel(EPackage pkg, String path) throws IOException { 
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet	.getResourceFactoryRegistry()
			.getExtensionToFactoryMap()
			.put("xmi", new XMIResourceFactoryImpl());
		Resource resource = resourceSet.createResource(URI.createFileURI(path));
		
		// to avoid ConcurrentModificationException
		EList classifiers = pkg.getEClassifiers();
		List<EClass> tempList = new ArrayList<EClass>();
		for(Object classifier : classifiers)
			tempList.add((EClass)classifier);
		for(EClass clazz : tempList)		
			resource.getContents().add(clazz);
		
		resource.save(null);
	}
	
	public void saveModel(EObject model, String path) throws IOException {
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
			.put("xmi", new XMIResourceFactoryImpl());
		Resource resource = resourceSet.createResource(URI.createFileURI(path));
		resource.getContents().add(model);
		resource.save(null);
	}
}
