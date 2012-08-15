package org.opaeum.javageneration.maps;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.ValuePin;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.name.NameConverter;

public class ActionMap extends ActivityNodeMap{

	private Action action;
	private PropertyMap targetMap;
	public ActionMap(OJUtil ojUtil,Action action){
		super(ojUtil, action);
		this.action = action;
	}
	public String getFireTimersMethod(){
		return super.doActionMethod();
	}
	public String getCancelEventsMethod(){
		return "cancelEventsFor" + action.getName();
	}
	public boolean targetIsImplicitObject(){
		NamedElement targetElement = EmfActionUtil.getTargetElement(action);
		if(targetElement instanceof ValuePin){
			return !(((ValuePin) targetElement).getValue() instanceof OpaqueExpression);
		}else if(targetElement instanceof InputPin){
			return ((InputPin) targetElement).getIncomings().isEmpty();
		}else if(targetElement != null){
			return false;
		}
		return true;
	}
	public PropertyMap targetMap(){
		if(targetMap == null){
			NamedElement te = EmfActionUtil.getTargetElement(action);
			if(te instanceof TypedElement){
				targetMap = ojUtil.buildStructuralFeatureMap((TypedElement) te);
			}
			// TODO Te instanceof Classifier
		}
		return targetMap;
	}
	public String targetName(){
		if(targetIsImplicitObject()){
			return implicitObject();
		}else{
			return "tgt" + NameConverter.capitalize(action.getName());
		}
	}
	private Classifier getExpectedTargetType(){
		if(action instanceof CallBehaviorAction){
			Behavior behavior = ((CallBehaviorAction) action).getBehavior();
			Activity a = EmfActivityUtil.getContainingActivity(action);
			if(a.getOwnedBehaviors().contains(behavior)){
				return a;
			}else if(a.getContext() != null && a.getContext().getOwnedBehaviors().contains(behavior)){
				return a.getContext();
			}else if(action.getInPartitions().size() == 1){
				Element r = action.getInPartitions().get(0).getRepresents();
				if(r instanceof Property && ((Property) r).getType() instanceof BehavioredClassifier){
					BehavioredClassifier bc = (BehavioredClassifier) ((Property) r).getType();
					if(bc.getOwnedBehaviors().contains(behavior)){
						return bc;
					}
				}else if(r instanceof BehavioredClassifier){
					if(((BehavioredClassifier) r).getOwnedBehaviors().contains(behavior)){
						return (Classifier) r;
					}
				}
			}
		}
		return EmfActionUtil.getExpectedTargetType(action);
	}
	private String implicitObject(){
		Classifier expectedTargetType = getExpectedTargetType();
		if(expectedTargetType != null){
			Activity a = getContainingActivity();
			if(EmfBehaviorUtil.hasExecutionInstance(a)){
				if(a.getContext() != null && a.getContext().conformsTo(expectedTargetType)){
					return "getContextObject()";
				}else if(a.conformsTo(expectedTargetType)){
					if(action.getOwner() instanceof StructuredActivityNode){
						return "getContainingActivity()";
					}else{
						return "this";
					}
				}
			}
		}
		return "this";
	}
	private Activity getContainingActivity(){
		return EmfActivityUtil.getContainingActivity(action);
	}
	public Action getAction(){
		return action;
	}
}