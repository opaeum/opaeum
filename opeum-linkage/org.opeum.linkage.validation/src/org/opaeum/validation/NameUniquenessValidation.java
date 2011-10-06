package org.opeum.validation;

import java.util.ArrayList;
import java.util.Collection;

import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.linkage.CoreValidationRule;
import org.opeum.metamodel.activities.INakedAction;
import org.opeum.metamodel.activities.INakedActivity;
import org.opeum.metamodel.activities.INakedActivityNode;
import org.opeum.metamodel.activities.INakedActivityVariable;
import org.opeum.metamodel.activities.INakedStructuredActivityNode;
import org.opeum.metamodel.core.INakedAssociation;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedElement;
import org.opeum.metamodel.core.INakedTypedElement;
import org.opeum.metamodel.core.PreAndPostConstrained;
import org.opeum.metamodel.statemachines.INakedStateMachine;

@StepDependency(phase = ValidationPhase.class)
public class NameUniquenessValidation extends AbstractValidator{
	// TODO parameters
	@VisitBefore(matchSubclasses = true)
	public void visitClassifier(INakedClassifier nc){
		ensureUniqueness(nc, "ownedRules", nc.getOwnedRules());
		if(!(nc instanceof INakedAssociation)){
			ensureUniqueness(nc, "ownedAttributes", nc.getOwnedAttributes());
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitPreAndPostConstraint(PreAndPostConstrained nc){
		Collection<INakedElement> o = new ArrayList<INakedElement>(nc.getPreConditions());
		o.addAll(nc.getPostConditions());
		ensureUniqueness(nc, "preconditions and postconditions", o);
	}
	@VisitBefore(matchSubclasses = true)
	public void visitAction(INakedAction nc){
		Collection<INakedElement> o = new ArrayList<INakedElement>(nc.getInput());
		o.addAll(nc.getOutput());
		ensureUniqueness(nc, "all input and output pins", o);
		for(INakedElement element:o){
			checkUniquenessInContext(nc, (INakedTypedElement) element);
		}
	}
	protected void checkUniquenessInContext(INakedElement ownerElement,INakedTypedElement pin){
		if(ownerElement instanceof INakedActivityNode){
			if(ownerElement instanceof INakedStructuredActivityNode){
				INakedStructuredActivityNode san = (INakedStructuredActivityNode) ownerElement;
				checkForNameClash(pin, san.getVariables());
			}
			checkUniquenessInContext((INakedElement) ownerElement.getOwnerElement(), pin);
		}else if(ownerElement instanceof INakedActivity){
			INakedActivity a = (INakedActivity) ownerElement;
			checkForNameClash(pin, a.getVariables());
		}
	}
	protected void checkForNameClash(INakedTypedElement pin,Collection<INakedActivityVariable> variables){
		for(INakedActivityVariable var:variables){
			if(var.getName().equals(pin.getName())){
				getErrorMap().putError(pin, CoreValidationRule.VARIABLE_NAME_CLASH, var.getName());
			}
		}
	}
	@VisitBefore(matchSubclasses=true)
	public void visitActivity(INakedActivity a){
		ensureUniqueness(a, "activity nodes", a.getActivityNodes());
	}
	@VisitBefore(matchSubclasses=true)
	public void visitStructuredActivityNode(INakedStructuredActivityNode a){
		ensureUniqueness(a, "activity nodes", a.getActivityNodes());
	}
	@VisitBefore(matchSubclasses = true)
	public void visitStateMachine(INakedStateMachine nc){
		ensureUniqueness(nc, "all states recursively", nc.getAllStates());
	}
	private void ensureUniqueness(INakedElement context,String feature,Collection<? extends INakedElement> ownedRules){
		for(INakedElement c1:ownedRules){
			if(c1.getName() == null || c1.getName().trim().length() == 1){
				getErrorMap().putError(c1, CoreValidationRule.NAME_REQIURED, c1.getMetaClass(), c1.getName());
			}else{
				for(INakedElement c2:ownedRules){
					if(c1.getName().equals(c2.getName()) && c1 != c2){
						getErrorMap().putError(c1, CoreValidationRule.NAME_UNIQUENESS, feature, context.getName());
						break;
					}
				}
			}
		}
	}
}
