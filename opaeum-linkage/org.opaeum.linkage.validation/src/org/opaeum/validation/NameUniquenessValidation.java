package org.opaeum.validation;

import java.util.ArrayList;
import java.util.Collection;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.linkage.CoreValidationRule;
import org.opaeum.metamodel.activities.INakedAction;
import org.opaeum.metamodel.activities.INakedActivity;
import org.opaeum.metamodel.activities.INakedActivityNode;
import org.opaeum.metamodel.activities.INakedActivityVariable;
import org.opaeum.metamodel.activities.INakedStructuredActivityNode;
import org.opaeum.metamodel.commonbehaviors.INakedBehavioredClassifier;
import org.opaeum.metamodel.core.INakedAssociation;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedTypedElement;
import org.opaeum.metamodel.core.PreAndPostConstrained;
import org.opaeum.metamodel.statemachines.INakedStateMachine;

@StepDependency(phase = ValidationPhase.class)
public class NameUniquenessValidation extends AbstractValidator{
	// TODO parameters
	@VisitBefore(matchSubclasses = true)
	public void visitClassifier(INakedClassifier nc){
		ensureUniqueness(nc, "ownedRules", nc.getOwnedRules());
		if(!(nc instanceof INakedAssociation)){
			ensureUniqueness(nc, "ownedAttributes", nc.getOwnedAttributes());
		}
		if(nc instanceof INakedBehavioredClassifier){
			ensureUniqueness(nc, "ownedBehaviors", ((INakedBehavioredClassifier) nc).getOwnedBehaviors());
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
