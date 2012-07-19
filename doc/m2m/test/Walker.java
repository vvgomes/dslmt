package br.pucrs.dslmt.m2m;

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

public class Walker {
	StringBuilder sb= new StringBuilder();
	
	public String walk(EObject model) {		
		EClass eclass= model.eClass();
		sb.append(eclass.getName() + "\n");
		
		Iterator<EStructuralFeature> it= eclass.getEStructuralFeatures().iterator();
		while(it.hasNext()) {
			EStructuralFeature f= it.next();
			if(f instanceof EReference) {
				EList children= (EList) model.eGet(f);
				for(Object child : children)
					walk((EObject) child);
			}
			else
				sb.append(model.eGet(f) + "\n");
		}
		return sb.toString();
	}
}
