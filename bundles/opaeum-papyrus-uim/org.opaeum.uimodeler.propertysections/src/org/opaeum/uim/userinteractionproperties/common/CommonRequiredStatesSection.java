package org.opaeum.uim.userinteractionproperties.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfStateMachineUtil;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.constraint.ConstraintFactory;
import org.opaeum.uim.constraint.ConstraintPackage;
import org.opaeum.uim.constraint.RootUserInteractionConstraint;
import org.opaeum.uim.util.UmlUimLinks;

public abstract class CommonRequiredStatesSection extends AbstractUmlReferenceLookupSection{
	public CommonRequiredStatesSection(){
		super();
	}

	@Override
	protected List<? extends EObject> getAvailableChoices(){
		UserInteractionElement ui = (UserInteractionElement) getFeatureOwner(getSelectedObject());
		Element umlElement = UmlUimLinks.getCurrentUmlLinks(ui).getNearestUmlElement(ui);
		StateMachine sm = EmfStateMachineUtil.getNearestApplicableStateMachine(umlElement);
		List<EObject> result = new ArrayList<EObject>();
		if(sm == null){
			Classifier c = EmfElementFinder.getNearestClassifier(umlElement);
			if(c instanceof BehavioredClassifier && ((BehavioredClassifier) c).getClassifierBehavior() instanceof StateMachine){
				sm=(StateMachine) ((BehavioredClassifier) c).getClassifierBehavior() ;
			}else if(c instanceof Behavior && ((Behavior) c).getContext()!=null){
				BehavioredClassifier bc=((Behavior) c).getContext();
				if(bc.getClassifierBehavior() instanceof StateMachine){
					sm=(StateMachine) bc.getClassifierBehavior();
				}
			}
			
		}
		if(sm!=null){
			Collection<State> allStates = EmfStateMachineUtil.getStatesRecursively(sm);
			result.addAll(allStates);
		}
		return result;
	}

	@Override
	protected UmlReference createNewReference(){
		return ConstraintFactory.eINSTANCE.createRequiredState();
	}

	protected List<? extends UmlReference> getCurrentUmlReferences(){
		if(getFeatureOwner(getSelectedObject()) != null){
			return ((RootUserInteractionConstraint) getFeatureOwner(getSelectedObject())).getRequiredStates();
		}else{
			return Collections.emptyList();
		}
	}

	@Override
	protected EStructuralFeature getFeature(){
		return ConstraintPackage.eINSTANCE.getRootUserInteractionConstraint_RequiredStates();
	}

	@Override
	public String getLabelText(){
		return "Required States";
	}
}