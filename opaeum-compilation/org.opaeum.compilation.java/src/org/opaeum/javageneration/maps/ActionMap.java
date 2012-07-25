package org.opaeum.javageneration.maps;

import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.javageneration.basicjava.simpleactions.ActivityNodeMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.name.NameConverter;

public class ActionMap extends ActivityNodeMap{
	public Action getAction(){
		return action;
	}
	private Action action;
	NakedStructuralFeatureMap targetMap;
	public ActionMap(Action action){
		super(action);
		this.action = action;
	}
	public String getFireTimersMethod(){
		return super.doActionMethod();
	}
	public String getCancelEventsMethod(){
		return "cancelEventsFor" + action.getName();
	}
	public boolean targetIsImplicitObject(){
		if(action instanceof IActionWithTargetElement){
			if(action.getInPartitions().size()==1){
				return false;
			}else if(action instanceof IActionWithTargetPin){
				IActionWithTargetPin a = (IActionWithTargetPin) action;
				if(a.getTarget() != null && a.getTarget().hasValidInput()){
					return false;
				}
			}
		}
		return true;
	}
	public NakedStructuralFeatureMap targetMap(){
		if(targetMap == null){
			if(action instanceof IActionWithTargetElement){
				if(action.getInPartitions().size()==1 && action.getInPartitions().get(0).getRepresents() instanceof Property){
					targetMap = OJUtil.buildStructuralFeatureMap((Property) action.getInPartitions().get(0).getRepresents());
				}else if(action instanceof IActionWithTargetPin && ((IActionWithTargetPin) action).getTarget() != null){
					targetMap = OJUtil.buildStructuralFeatureMap(getContainingActivity(), ((IActionWithTargetPin) action).getTarget());
				}
			}
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
		if(action instanceof IActionWithTargetPin){
			return ((IActionWithTargetPin) action).getExpectedTargetType();
		}else if(action instanceof CallBehaviorAction){
			return ((CallBehaviorAction) action).getBehavior().getContext();
		}else{
			return null;
		}
	}
	private String implicitObject(){
		if(getExpectedTargetType() != null){
			if(EmfBehaviorUtil.hasExecutionInstance(getContainingActivity())){
				if(getContainingActivity().getContext() != null && getContainingActivity().getContext().conformsTo(getExpectedTargetType())){
					return "getContextObject()";
				}else if(action.getOwner() instanceof StructuredActivityNode){
					return "getContainingActivity()";
				}
			}
		}
		return "this";
	}
	private Activity getContainingActivity(){
		return EmfActivityUtil.getContainingActivity(action);
	}
	void setAction(IActionWithTargetElement action){
		this.action = action;
	}
}