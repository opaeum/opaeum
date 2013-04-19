/**
 * 
 */
package org.opaeum.emf.validation;

import java.util.ArrayList;
import java.util.List;

import nl.klasse.octopus.model.IModelElement;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintSeverity;
import org.eclipse.emf.validation.model.EvaluationMode;
import org.eclipse.emf.validation.model.IModelConstraint;
import org.eclipse.emf.validation.service.AbstractConstraintDescriptor;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.metamodel.validation.BrokenElement;
import org.opaeum.metamodel.validation.ErrorMap;
import org.opaeum.metamodel.validation.IValidationRule;
import org.opaeum.name.NameConverter;

final class ConstraintAdapter extends AbstractConstraintDescriptor implements IModelConstraint{
	private static int statusCode = 0;
	private int myStatusCode = statusCode++;
	EClass[] eClass;
	IValidationRule rule;
	public ConstraintAdapter(IValidationRule rule,EClass...class1){
		super();
		eClass = class1;
		this.rule = rule;
	}
	public String getId(){
		return rule.getDeclaringClass().getName() + "." + rule.name();
	}
	public String getDescription(){
		return rule.getDescription();
	}
	public boolean targetsTypeOf(EObject eObject){
		for(int i = 0;i < eClass.length;i++){
			if(eClass[i].isInstance(eObject)){
				return true;
			}
		}
		return true;
	}
	public String getMessagePattern(){
		if(rule.getMessagePattern() != null){
			return rule.getMessagePattern();
		}
		return "{0} is invalid:{1}";
	}
	public String getName(){
		return NameConverter.separateWords(rule.name());
	}
	public IStatus validate(IValidationContext ctx){
		try{
			Element e = (Element) ctx.getTarget();
			Model m = e.getModel();
			if(m != null){
				ErrorMap validator = OpaeumEclipseContext.getCurrentContext().getNakedWorkspace().getErrorMap();
				if(validator.hasBroken(rule, e)){
					BrokenElement be = validator.getErrors().get(e);
					List<Object> messageParams = new ArrayList<Object>();
					messageParams.add(ctx.getTarget());
					for(Object o:be.getMessageParameters(rule)){
						if(o instanceof IModelElement){
							//TODO fix this
//							messageParams.add(validator.getSourceElement((IModelElement) o));
						}else{
							messageParams.add(o);
						}
					}
					return ctx.createFailureStatus(messageParams.toArray());
				}
			}
			return ctx.createSuccessStatus();
		}catch(RuntimeException e){
			e.printStackTrace();
			return ctx.createFailureStatus(new Object[]{e.getMessage()});
		}
	}
	public String getPluginId(){
		return "org.opaeum.metamodel";
	}
	public ConstraintSeverity getSeverity(){
		return ConstraintSeverity.ERROR;
	}
	public int getStatusCode(){
		return myStatusCode;
	}
	public boolean targetsEvent(Notification notification){
		return false;
	}
	public String getBody(){
		return "";
	}
	public EvaluationMode<?> getEvaluationMode(){
		return EvaluationMode.BATCH;
	}
}