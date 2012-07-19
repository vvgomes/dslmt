package br.pucrs.dslmt.xmi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

@SuppressWarnings("unchecked")

public class XmiReader {
	
	public EPackage readMetamodel(String path) throws IOException {
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
			.put("xmi", new XMIResourceFactoryImpl());
		Resource resource = resourceSet.createResource(URI.createFileURI(path));
		resource.load(null);
		EList rawContents = resource.getContents();
		return createPackage(rawContents);
	}
	
	public EObject readModel(String path, EPackage metamodel) throws IOException {
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
			.put("xmi", new XMIResourceFactoryImpl());
		resourceSet.getPackageRegistry().put(metamodel.getNsURI(), metamodel);
		Resource resource = resourceSet.createResource(URI.createFileURI(path));
		resource.load(null);
		EList rawContents = resource.getContents();
		EObject root = (EObject) rawContents.get(0);
		return root;
	}
	
	private EPackage createPackage(EList rawContents) throws IOException {		
		EClass root = (EClass)rawContents.get(0);
		EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;
		EPackage pkg = ecoreFactory.createEPackage();
		pkg.setName(root.getName());
		pkg.setNsPrefix(root.getName());
		pkg.setNsURI("br.pucrs");
		
		// to avoid ConcurrentModificationException (fix it!)
		List<EClass> tempList = new ArrayList<EClass>();
		for(Object clazz : rawContents)
			tempList.add((EClass)clazz);
		for(EClass clazz : tempList)		
			pkg.getEClassifiers().add(clazz);
		return pkg;
	}
}
