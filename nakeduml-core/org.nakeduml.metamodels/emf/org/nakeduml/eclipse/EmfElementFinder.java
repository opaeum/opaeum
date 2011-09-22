package org.nakeduml.eclipse;

import java.util.ArrayList;
import java.util.List;

import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.CallEvent;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Profile;
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
		List<TypedElement> result = new ArrayList<TypedElement>(getPropertiesInScope(c));
		if(c instanceof Behavior){
			Behavior b = (Behavior) c;
			result.addAll(b.getOwnedParameters());
			if(b.getContext() != null){
				result.addAll(getPropertiesInScope(b.getContext()));
			}
		}
		return result;
	}
	public static Classifier getNearestClassifier(Element e){
		if(e instanceof Classifier){
			return (Classifier) e;
		}else if(e ==null){
			return null;
		}else{
			return getNearestClassifier((Element) getContainer(e));
		}
	}
	public static org.eclipse.uml2.uml.Package getRootObject(Element e){
		if(e instanceof Model || e instanceof Profile){
			return (org.eclipse.uml2.uml.Package) e;
		}else if(e ==null){
			return null;
		}else{
			return (Package) getRootObject((Element) getContainer(e));
		}
	}
	public static List<TypedElement> getTypedElementsInScope(Element behavioralElement){
		List<TypedElement> result = new ArrayList<TypedElement>();
		if(behavioralElement != null){
			Element a = behavioralElement;
			if(a instanceof Constraint){
				if(a.getOwner() instanceof Action){
					Action act = (Action) a.getOwner();
					result.addAll(act.getInputs());
					if(act.getLocalPostconditions().contains(a)){
						result.addAll(act.getOutputs());
					}
					return result;
				}
			}
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
	public static List<Property> getPropertiesInScope(Classifier c){
		List<Property> result = new ArrayList<Property>(c.getAttributes());
		for(Association a:c.getAssociations()){
			for(Property end:a.getNavigableOwnedEnds()){
				if(end.getOtherEnd().getType().equals(c)){
					result.add(end);
				}
			}
		}
		for(Generalization ir:c.getGeneralizations()){
			result.addAll(getPropertiesInScope(ir.getGeneral()));
		}
		if(c instanceof org.eclipse.uml2.uml.Class){
			org.eclipse.uml2.uml.Class cls = (Class) c;
			for(InterfaceRealization ir:cls.getInterfaceRealizations()){
				result.addAll(getPropertiesInScope(ir.getContract()));
			}
		}
		return result;
	}
	public static EObject getContainer(EObject s){
		if(s == null){
			return null;
		}else if(s instanceof Event){
			org.eclipse.uml2.uml.Event event = (org.eclipse.uml2.uml.Event) s;
			// Contained by an annotation inside another element?
			if(event.eContainer() instanceof EAnnotation){
				// Skip event AND annotation straight to the containing element
				EAnnotation ea = (EAnnotation) event.eContainer();
				return ea.getEModelElement();
			}else{
				// Old strategy - could be problematic if the event is referenced from multiple triggers
				EAnnotation ann = event.getEAnnotation(StereotypeNames.NUML_ANNOTATION);
				if(ann != null){
					for(EObject eObject:ann.getReferences()){
						if(eObject instanceof Trigger){
							return eObject;
						}
					}
				}
			}
			return null;
			// throw new IllegalStateException("No context could be found for Event:" + event.getQualifiedName());
		}else if(s.eContainer() instanceof EAnnotation){
			return ((EAnnotation) s.eContainer()).getEModelElement();
		}else if(s instanceof Property && s.eContainer() instanceof Association){
			Property p = (Property) s;
			if(p.getOtherEnd() != null && p.isNavigable()){
				return p.getOtherEnd().getType();
			}else{
				return s.eContainer();
			}
		}else if(s instanceof InterfaceRealization){
			return ((InterfaceRealization) s).getImplementingClassifier();
		}else if(s instanceof Generalization){
			return ((Generalization) s).getSpecific();
		}
		return s.eContainer();
	}
}
