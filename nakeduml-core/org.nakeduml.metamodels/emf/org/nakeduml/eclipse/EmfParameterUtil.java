package org.nakeduml.eclipse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.CallEvent;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.SignalEvent;
import org.eclipse.uml2.uml.TypedElement;

public class EmfParameterUtil{
	public static final int EXCEPTION = 0;
	public static final int ARGUMENT = 1;
	public static final int RESULT = 2;
	/**
	 * 
	 * Unlike Ocl, Octopus and Java, UML2 does not distinguish between return paramaters and other parameters
	 * 
	 */
	public static int calculateIndex(TypedElement emfParameter,int parameterKind){
		int index = 0;
		if(emfParameter instanceof Parameter){
			List<Parameter> ownedParameter = getContainerList((Parameter) emfParameter);
			for(int i = 0;i < ownedParameter.size();i++){
				Parameter p = (Parameter) ownedParameter.get(i);
				if(shouldCountParameter(parameterKind, p)){
					if(p.equals(emfParameter)){
						break;
					}else{
						index++;
					}
				}
			}
		}else{
			Property p = (Property) emfParameter;
			List<Property> ownedAttributes = new ArrayList<Property>();
			Element owner = p.getOwner();
			Classifier owningClass = null;
			if(owner instanceof Association){
				owningClass = (Classifier) p.getOtherEnd().getType();
				addInheritiedAttributes(ownedAttributes, owningClass);
			}else if(owner instanceof Classifier){
				owningClass = (Classifier) owner;
				addInheritiedAttributes(ownedAttributes, owningClass);
			}
			index = ownedAttributes.indexOf(p);
		}
		return index;
	}
	public static List<Property> getArguments(Signal s){
		List<Property> result = new ArrayList<Property>();
		addInheritiedAttributes(result, s);
		return result;
	}
	public static int calculateOwnedAttributeIndex(Property assEnd){
		List<Property> ownedAttributes = new ArrayList<Property>();
		Element owner = assEnd.getOwner();
		if(owner instanceof Association){
			addNonInhertiedAttributes(ownedAttributes, (Classifier) assEnd.getOtherEnd().getType());
		}else if(owner instanceof Classifier){
			addNonInhertiedAttributes(ownedAttributes, (Classifier) owner);
		}
		return ownedAttributes.indexOf(assEnd);
	}
	private static void addNonInhertiedAttributes(List<Property> ownedAttributes,Classifier owner){
		Classifier c = owner;
		if(owner instanceof Signal){
			ownedAttributes.addAll(((Signal) owner).getOwnedAttributes());
		}else if(owner instanceof DataType){
			ownedAttributes.addAll(((DataType) owner).getOwnedAttributes());
		}else if(owner instanceof org.eclipse.uml2.uml.Class){
			ownedAttributes.addAll(((org.eclipse.uml2.uml.Class) owner).getOwnedAttributes());
		}
		for(Association a:((Classifier) owner).getAssociations()){
			for(Property p:a.getMemberEnds()){
				if(p.isNavigable() &&  p.getOtherEnd().getType() == owner){
					ownedAttributes.add(p);
				}
			}
		}
	}
	private static void addInheritiedAttributes(List<Property> list,Classifier c){
		for(Generalization g:c.getGeneralizations()){
			addInheritiedAttributes(list, g.getGeneral());
		}
		addNonInhertiedAttributes(list, c);
	}
	private static List<Parameter> getContainerList(Parameter emfParameter){
		List<Parameter> ownedParameter = null;
		if(emfParameter.getOperation() == null){
			if(emfParameter.getOwner() instanceof Behavior){
				ownedParameter = ((Behavior) emfParameter.getOwner()).getOwnedParameters();
			}else{
				throw new IllegalStateException("Unknown owner for parameter:" + emfParameter.getOwner());
			}
		}else{
			ownedParameter = emfParameter.getOperation().getOwnedParameters();
		}
		return ownedParameter;
	}
	private static boolean shouldCountParameter(int paramaterKind,Parameter p){
		switch(paramaterKind){
		case EXCEPTION:
			return (p.getDirection().equals(ParameterDirectionKind.OUT_LITERAL) || p.getDirection().equals(ParameterDirectionKind.RETURN_LITERAL)) && p.isException();
		case ARGUMENT:
			return p.getDirection().equals(ParameterDirectionKind.IN_LITERAL) || p.getDirection().equals(ParameterDirectionKind.INOUT_LITERAL);
		case RESULT:
			return p.getDirection().equals(ParameterDirectionKind.OUT_LITERAL) || p.getDirection().equals(ParameterDirectionKind.INOUT_LITERAL)
					|| p.getDirection().equals(ParameterDirectionKind.RETURN_LITERAL);
		default:
			return false;
		}
	}
	public static List<? extends TypedElement> getArguments(Element origin){
		if(origin instanceof CallEvent){
			return getArguments(((CallEvent) origin).getOperation());
		}else if(origin instanceof SignalEvent){
			return getArguments(((SignalEvent) origin).getSignal());
		}else if(origin instanceof Signal){
			return getArguments((Signal) origin);
		}else if(origin instanceof Operation){
			List<Parameter> result = new ArrayList<Parameter>();
			for(Parameter p:((Operation) origin).getOwnedParameters()){
				if(isArgument(p.getDirection())){
					result.add(p);
				}
			}
			return result;
		}else{
			return Collections.emptyList();
		}
	}
	public static boolean isArgument(ParameterDirectionKind direction){
		return direction == ParameterDirectionKind.IN_LITERAL || direction == ParameterDirectionKind.INOUT_LITERAL;
	}
	public static boolean isResult(ParameterDirectionKind direction){
		return direction == ParameterDirectionKind.OUT_LITERAL || direction == ParameterDirectionKind.INOUT_LITERAL || direction == ParameterDirectionKind.RETURN_LITERAL;
	}
}
