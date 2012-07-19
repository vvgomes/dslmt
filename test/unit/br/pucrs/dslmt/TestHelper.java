package br.pucrs.dslmt;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;

import br.pucrs.dslmt.util.TextFileIo;

public class TestHelper {
	EPackage fsmMetamodel;
	EObject fsmModel;
	String fsmText;
	String fsmGrammar;
	Map<String, String> templates;
	TextFileIo io= new TextFileIo();
	
	public EPackage getFsmMetamodel() {
		if(fsmMetamodel == null)
			createFsmMetamodel();
		return fsmMetamodel;
	}
	
	public EObject getFsmModel() {
		if(fsmModel == null)
			createFsmModel();
		return fsmModel;
	}
	
	public String getFsmText() throws Exception {
		if(fsmText == null)
			createFsmText();
		return fsmText;
	}
	
	public String getFsmGrammar() throws Exception  {
		if(fsmGrammar == null)
			createFsmGrammar();
		return fsmGrammar;
	}
	
	public Map<String, String> getTemplates() throws Exception {
		if(templates == null)
			createTemplates();
		return templates;
	}
	
	public String getTransitionGrammar() {
		return 
			"transition returns [EObject node]\n"+
			"\t@init {\n"+
			"\tEClass eClass= (EClass)pkg.getEClassifier(\"transition\");\n"+
			"\t$node= pkg.getEFactoryInstance().create(eClass);\n"+
			"\t}\n"+
			"\t:\n"+
			"\t'transition'\n"+ 
			"\tinputt=CHAR {$node.eSet(eClass.getEStructuralFeature(\"inputt\"), removeQuotes($inputt.text));}\n"+
			"\t'/'\n"+
			"\toutput=CHAR {$node.eSet(eClass.getEStructuralFeature(\"output\"), removeQuotes($output.text));}\n"+ 
			"\t'->'\n"+
			"\ttarget=ID {$node.eSet(eClass.getEStructuralFeature(\"target\"), $target.text);}\n"+
			"\t;";
	}
	
	public boolean sameContent(String path, String otherPath) throws Exception {
		String content= io.read(path);
		String otherContent= io.read(otherPath);
		return content.equals(otherContent);
	}
	
	private void createFsmGrammar() throws Exception {
		fsmGrammar= io.read("models/T.g");
	}
	
	private void createTemplates() throws Exception {
		templates= new HashMap<String, String>();
		templates.put("fsm", io.read("models/templates/fsm.st"));
		templates.put("state", io.read("models/templates/state.st"));
		templates.put("transition", io.read("models/templates/transition.st"));
	}
	
	private void createFsmMetamodel() {
		EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;
		EcorePackage ecorePackage = EcorePackage.eINSTANCE;
		
		fsmMetamodel = ecoreFactory.createEPackage();
		fsmMetamodel.setName("fsm");
		fsmMetamodel.setNsPrefix("fsm");
		fsmMetamodel.setNsURI("br.pucrs");
		
		EClass fsm= ecoreFactory.createEClass();
		fsm.setName("fsm");
		fsmMetamodel.getEClassifiers().add(fsm);
		
		EAttribute inputAlphabet = ecoreFactory.createEAttribute();
		inputAlphabet.setName("inputAlphabet");
		inputAlphabet.setEType(ecorePackage.getEString());
		fsm.getEStructuralFeatures().add(inputAlphabet);
		
		EAttribute outputAlphabet = ecoreFactory.createEAttribute();
		outputAlphabet.setName("outputAlphabet");
		outputAlphabet.setEType(ecorePackage.getEString());
		fsm.getEStructuralFeatures().add(outputAlphabet);
		
		EClass state= ecoreFactory.createEClass();
		state.setName("state");
		fsmMetamodel.getEClassifiers().add(state);
		
		EAttribute id = ecoreFactory.createEAttribute();
		id.setName("id");
		id.setEType(ecorePackage.getEString());
		state.getEStructuralFeatures().add(id);
		
		EAttribute start = ecoreFactory.createEAttribute();
		start.setName("start");
		start.setEType(ecorePackage.getEBoolean());
		state.getEStructuralFeatures().add(start);
		
		EClass transition= ecoreFactory.createEClass();
		transition.setName("transition");
		fsmMetamodel.getEClassifiers().add(transition);
		
		EAttribute inputt = ecoreFactory.createEAttribute();
		inputt.setName("inputt");
		inputt.setEType(ecorePackage.getEChar());
		transition.getEStructuralFeatures().add(inputt);
		
		EAttribute output = ecoreFactory.createEAttribute();
		output.setName("output");
		output.setEType(ecorePackage.getEChar());
		transition.getEStructuralFeatures().add(output);
		
		EAttribute target = ecoreFactory.createEAttribute();
		target.setName("target");
		target.setEType(ecorePackage.getEString());
		transition.getEStructuralFeatures().add(target);
				
		EReference fsmHasStates = ecoreFactory.createEReference();
		fsmHasStates.setName(state.getName());
		fsmHasStates.setEType(state);
		fsmHasStates.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
		fsmHasStates.setContainment(true);
		fsm.getEStructuralFeatures().add(fsmHasStates);
		
		EReference stateHasTransitions = ecoreFactory.createEReference();
		stateHasTransitions.setName(transition.getName());
		stateHasTransitions.setEType(transition);
		stateHasTransitions.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
		stateHasTransitions.setContainment(true);
		state.getEStructuralFeatures().add(stateHasTransitions);
	}
	
	private void createFsmModel() {
		if(fsmMetamodel == null) 
			createFsmMetamodel();
		
		EClass fsm= (EClass) fsmMetamodel.getEClassifier("fsm");
		EClass state= (EClass) fsmMetamodel.getEClassifier("state");
		EClass transition= (EClass) fsmMetamodel.getEClassifier("transition");
		EFactory factory = fsmMetamodel.getEFactoryInstance();
		
		fsmModel = factory.create(fsm);
		fsmModel.eSet(fsm.getEStructuralFeature("inputAlphabet"), "01");
		fsmModel.eSet(fsm.getEStructuralFeature("outputAlphabet"), "eo");
		
		EObject even = factory.create(state);
		even.eSet(state.getEStructuralFeature("id"), "Even");
		even.eSet(state.getEStructuralFeature("start"), true);
		((EList)fsmModel.eGet(fsm.getEStructuralFeature("state"))).add(even);
		
		EObject evenToOdd = factory.create(transition);
		evenToOdd.eSet(transition.getEStructuralFeature("inputt"), '0');
		evenToOdd.eSet(transition.getEStructuralFeature("output"), 'o');
		evenToOdd.eSet(transition.getEStructuralFeature("target"), "Odd");
		((EList)even.eGet(state.getEStructuralFeature("transition"))).add(evenToOdd);
		
		EObject evenToEven = factory.create(transition);
		evenToEven.eSet(transition.getEStructuralFeature("inputt"), '1');
		evenToEven.eSet(transition.getEStructuralFeature("output"), 'e');
		evenToEven.eSet(transition.getEStructuralFeature("target"), "Even");
		((EList)even.eGet(state.getEStructuralFeature("transition"))).add(evenToEven);
		
		EObject odd = factory.create(state);
		odd.eSet(state.getEStructuralFeature("id"), "Odd");
		odd.eSet(state.getEStructuralFeature("start"), false);
		((EList)fsmModel.eGet(fsm.getEStructuralFeature("state"))).add(odd);
		
		EObject oddToEven = factory.create(transition);
		oddToEven.eSet(transition.getEStructuralFeature("inputt"), '0');
		oddToEven.eSet(transition.getEStructuralFeature("output"), 'e');
		oddToEven.eSet(transition.getEStructuralFeature("target"), "Even");
		((EList)odd.eGet(state.getEStructuralFeature("transition"))).add(oddToEven);
		
		EObject oddToOdd = factory.create(transition);
		oddToOdd.eSet(transition.getEStructuralFeature("inputt"), '1');
		oddToOdd.eSet(transition.getEStructuralFeature("output"), 'o');
		oddToOdd.eSet(transition.getEStructuralFeature("target"), "Odd");
		((EList)odd.eGet(state.getEStructuralFeature("transition"))).add(oddToOdd);
	}
	
	private void createFsmText() throws Exception {
		fsmText= io.read("models/fsm_model.fsm");
	}
}