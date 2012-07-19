package br.pucrs.dslmt.m2t;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.antlr.stringtemplate.language.DefaultTemplateLexer;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;

import br.pucrs.dslmt.textualSpec.ConstantSpec;
import br.pucrs.dslmt.textualSpec.EAttributeSpec;
import br.pucrs.dslmt.textualSpec.EClassSpec;
import br.pucrs.dslmt.textualSpec.EReferenceSpec;
import br.pucrs.dslmt.textualSpec.SpecElement;
import br.pucrs.dslmt.textualSpec.TextualSpec;

public class TemplateProcessor {
	private TextualSpec spec;
	private StringTemplateGroup group;
	private String output= "";
	
	public TemplateProcessor() {
		group= new StringTemplateGroup("myGroup", "src/br/pucrs/dslmt/m2t", DefaultTemplateLexer.class);
	}
	
	public void process(TextualSpec spec, EObject model) {
		this.spec= spec;
		String rootName= this.spec.getRootEClassName();
		EClassSpec rootSpec= this.spec.getSpecFor(rootName);
		StringTemplate current= group.getInstanceOf(rootName);
		processTemplateFor(rootSpec, current, model);
		output= current.toString();
	}
	
	public String getOutput() {
		return output;
	}
	
	private void processTemplateFor(EClassSpec s, StringTemplate current, EObject model) {
		for(SpecElement e : s.getElements())
			processTemplateFor(e, current, model);
	}
	
	private void processTemplateFor(SpecElement e, StringTemplate current, EObject model) {
		if(e instanceof EAttributeSpec)
			processTemplateFor((EAttributeSpec)e, current, model);
		else if(e instanceof EReferenceSpec)
			processTemplateFor((EReferenceSpec)e, current, model);
		else if(e instanceof ConstantSpec)
			processTemplateFor((ConstantSpec)e, current, model);
	}
	
	private void processTemplateFor(EAttributeSpec s, StringTemplate template, EObject model) {
		String attr= s.getEAttributeName();
		template.setAttribute(attr, model.eGet(model.eClass().getEStructuralFeature(attr)));
	}
	
	private void processTemplateFor(ConstantSpec s, StringTemplate template, EObject model) {
		EAttribute associated= s.getAssociatedAttr();
		if(associated != null) {
			String attr= associated.getName();
			template.setAttribute(attr, model.eGet(model.eClass().getEStructuralFeature(attr)));
		}		
	}
	
	private void processTemplateFor(EReferenceSpec s, StringTemplate template, EObject model) {
		String ref= s.getEReferenceName();
		EList children= (EList) model.eGet(model.eClass().getEStructuralFeature(ref));
		for(Object child : children) {
			EObject childModel= (EObject) child;
			String name= childModel.eClass().getName();
			EClassSpec childSpec= spec.getSpecFor(name);
			StringTemplate current= group.getInstanceOf(name);
			processTemplateFor(childSpec, current, childModel);
			template.setAttribute(ref, current.toString());
		}
	}
}
