package org.nakeduml.eclipse;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.CallEvent;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.SignalEvent;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.UMLFactory;

public class EmfElementFinder{
	public static List<TypedElement> getTypedElementsInScope(Classifier c){
		List<TypedElement> result = getPropertiesInScope(c);
		if(c instanceof Behavior){
			Behavior b = (Behavior) c;
			result.addAll(b.getOwnedParameters());
			if(b.getContext() != null){
				result.addAll(getPropertiesInScope(b.getContext()));
			}
		}
		return result;
	}
	public static List<TypedElement> getTypedElementsInScope(Element behavioralElement){
		List<TypedElement> result = new ArrayList<TypedElement>();
		if(behavioralElement != null){
			Element a = behavioralElement;
			while(!(a == null || a instanceof Classifier)){
				if(a instanceof StructuredActivityNode){
					result.addAll(((StructuredActivityNode) a).getVariables());
				}
				if(a instanceof Transition){
					addTransitionParameters(result, (Transition) a);
				}
				a = a.getOwner();
			}
			if(a instanceof Behavior){
				result.addAll(((Behavior) a).getOwnedParameters());
				if(a instanceof Activity){
					Activity activity = (Activity) a;
					result.addAll(activity.getVariables());
				}
				if(a.getOwner() instanceof Transition){
					Transition owner = (Transition) a.getOwner();
					addTransitionParameters(result, owner);
					a = EmfStateMachineUtil.getStateMachine(a.getOwner());
				}else if(a.getOwner() instanceof State){
					a = EmfStateMachineUtil.getStateMachine(a.getOwner());
				}
			}
			if(a != null){
				result.addAll(getTypedElementsInScope((Classifier) a));
			}
		}
		return result;
	}
	protected static void addTransitionParameters(List<TypedElement> result,Transition a){
		EList<Trigger> triggers = a.getTriggers();
		if(triggers.size() > 0){
			Event event = triggers.get(0).getEvent();
			if(event instanceof CallEvent){
				result.addAll(((CallEvent) event).getOperation().getOwnedParameters());
			}else if(event instanceof SignalEvent){
				for(Property p:((SignalEvent) event).getSignal().getAllAttributes()){
					// Create parameter to emulate parameter behavior in ocl, "self" would be invalid
					Parameter param = UMLFactory.eINSTANCE.createParameter();
					param.setType(p.getType());
					param.setName(p.getName());
					result.add(param);
				}
			}
		}
	}
	public static List<TypedElement> getPropertiesInScope(Classifier c){
		List<TypedElement> result = new ArrayList<TypedElement>(c.getAllAttributes());
		for(Association a:c.getAssociations()){
			for(Property end:a.getNavigableOwnedEnds()){
				if(end.getOtherEnd().getType().equals(c)){
					result.add(end);
				}
			}
		}
		return result;
	}
	private static boolean matches(Classifier c,EObject eObject){
		boolean found = false;
		return found;
	}
}
