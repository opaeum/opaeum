package org.opaeum.validation;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.Variable;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfStateMachineUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.linkage.CoreValidationRule;

@StepDependency(phase = ValidationPhase.class)
public class NameUniquenessValidation extends AbstractValidator{
	// TODO parameters
	@VisitBefore(matchSubclasses = true)
	public void visitClassifier(Classifier nc){
		ensureUniqueness(nc, "ownedRules", nc.getOwnedRules());
		if(!(nc instanceof Association)){
			ensureUniqueness(nc, "ownedAttributes", nc.getAttributes());
		}
		if(nc instanceof BehavioredClassifier){
			ensureUniqueness(nc, "ownedBehaviors", ((BehavioredClassifier) nc).getOwnedBehaviors());
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitPreAndPostConstraint(Behavior nc){
		Collection<NamedElement> o = new ArrayList<NamedElement>(nc.getPreconditions());
		o.addAll(nc.getPostconditions());
		ensureUniqueness(nc, "preconditions and postconditions", o);
		ensureUniqueness(nc, "ownedParameters", nc.getOwnedParameters());
	}
	@VisitBefore(matchSubclasses = true)
	public void visitPreAndPostConstraint(Operation nc){
		Collection<NamedElement> o = new ArrayList<NamedElement>(nc.getPreconditions());
		o.addAll(nc.getPostconditions());
		ensureUniqueness(nc, "preconditions and postconditions", o);
		ensureUniqueness(nc, "ownedParameters", nc.getOwnedParameters());
	}
	@VisitBefore(matchSubclasses = true)
	public void visitAction(Action nc){
		Collection<NamedElement> o = new ArrayList<NamedElement>(nc.getInputs());
		o.addAll(nc.getOutputs());
		ensureUniqueness(nc, "all input and output pins", o);
		for(Element element:o){
			checkUniquenessInContext(nc, (TypedElement) element);
		}
		Collection<NamedElement> c = new ArrayList<NamedElement>(nc.getLocalPreconditions());
		o.addAll(nc.getLocalPostconditions());
		ensureUniqueness(nc, "preconditions and postconditions", c);
	}
	protected void checkUniquenessInContext(Element ownerElement,TypedElement pin){
		if(ownerElement instanceof ActivityNode){
			if(ownerElement instanceof StructuredActivityNode){
				StructuredActivityNode san = (StructuredActivityNode) ownerElement;
				checkForNameClash(pin, san.getVariables());
			}
			checkUniquenessInContext((Element) EmfElementFinder.getContainer(ownerElement), pin);
		}else if(ownerElement instanceof Activity){
			Activity a = (Activity) ownerElement;
			checkForNameClash(pin, a.getVariables());
		}
	}
	protected void checkForNameClash(TypedElement pin,Collection<Variable> variables){
		for(Variable var:variables){
			if(var.getName().equals(pin.getName())){
				getErrorMap().putError(pin, CoreValidationRule.VARIABLE_NAME_CLASH, var.getName());
			}
		}
	}
	@VisitBefore(matchSubclasses=true)
	public void visitActivity(Activity a){
		ensureUniqueness(a, "activity nodes", a.getOwnedNodes());
	}
	@VisitBefore(matchSubclasses=true)
	public void visitStructuredActivityNode(StructuredActivityNode a){
		ensureUniqueness(a, "activity nodes", a.getContainedNodes());
	}
	@VisitBefore(matchSubclasses = true)
	public void visitStateMachine(StateMachine nc){
		ensureUniqueness(nc, "all states recursively", EmfStateMachineUtil.getAllStates( nc));
	}
	private void ensureUniqueness(NamedElement context,String feature,Collection<? extends NamedElement> ownedRules){
		for(NamedElement c1:ownedRules){
			if(c1.getName() == null || c1.getName().trim().length() == 1){
				getErrorMap().putError(c1, CoreValidationRule.NAME_REQIURED, EmfClassifierUtil.getMetaClass( c1), c1.getName());
			}else{
				for(NamedElement c2:ownedRules){
					if(c1.getName().equals(c2.getName()) && c1 != c2){
						getErrorMap().putError(c1, CoreValidationRule.NAME_UNIQUENESS, feature, context.getName());
						break;
					}
				}
			}
		}
	}
}
