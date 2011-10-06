package org.opeum.eclipse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.TypedElement;

public class EmfPropertyUtil{
	public static Property getEndToComposite(Classifier c){
		Property result = getImmediateEndToComposite(c);
		if(result == null){
			Iterator<Classifier> classes = c.getGenerals().iterator();
			while(classes.hasNext() && result == null){
				result = getImmediateEndToComposite(classes.next());
			}
			if(result == null && c instanceof BehavioredClassifier){
				Iterator<Interface> interfaces = ((BehavioredClassifier) c).getImplementedInterfaces().iterator();
				while(interfaces.hasNext() && result == null){
					result = getImmediateEndToComposite(interfaces.next());
				}
			}
		}
		return result;
	}
	private static Property getImmediateEndToComposite(Classifier c){
		Property result = null;
		for(Association association:c.getAssociations()){
			for(Property property:association.getMemberEnds()){
				if(property.getType() == c && property.isComposite()){
					result = property.getOtherEnd();
				}
			}
		}
		return result;
	}
	public static boolean isMany(TypedElement typedElement){
		MultiplicityElement me = (MultiplicityElement) typedElement;
		if(typedElement instanceof Property){
			if(((Property) typedElement).getQualifiers().size() > 0){
				return true;
			}
		}
		boolean many = me.getUpper() == -1 || me.getUpper() > 1;
		return many;
	}
	public static boolean isComposite(TypedElement t){
		if(t instanceof Property){
			Property p = (Property) t;
			return p.isComposite();
		}
		return false;
	}
	@SuppressWarnings({
			"unchecked","rawtypes"
	})
	public static Collection<TypedElement> getOwnedTypedElements(Element e){
		if(e instanceof Class){
			Class class1 = (Class) e;
			if(class1 != null){
				return new ArrayList<TypedElement>((Collection<Property>) (Collection) EmfElementFinder.getPropertiesInScope(class1));
			}
		}else if(e instanceof State){
			State class1 = (State) e;
			if(class1 != null){
				return new ArrayList<TypedElement>(EmfStateMachineUtil.getStateMachine(class1).getAllAttributes());
			}
		}else if(e instanceof Operation){
			Operation operation = (Operation) e;
			if(operation != null){
				return new ArrayList<TypedElement>(operation.getOwnedParameters());
			}
		}else if(e instanceof Operation){
			Operation operation = (Operation) e;
			if(operation != null){
				return new ArrayList<TypedElement>(operation.getOwnedParameters());
			}
		}else if(e instanceof OpaqueAction){
			OpaqueAction action = (OpaqueAction) e;
			if(action != null){
				Collection<Pin> result = new HashSet<Pin>(action.getInputs());
				result.addAll(action.getOutputs());
			}
		}
		return Collections.emptySet();
	}
}
