package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.IActionWithTargetElement;
import net.sf.nakeduml.metamodel.actions.IActionWithTargetPin;
import net.sf.nakeduml.metamodel.actions.INakedCallBehaviorAction;
import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedProperty;

public class ActionMap extends ActivityNodeMap{
	public INakedAction getAction(){
		return action;
	}
	private INakedAction action;
	NakedStructuralFeatureMap targetMap;
	public ActionMap(INakedAction action){
		super(action);
		this.action = action;
	}
	public String getFireTimersMethod(){
		return super.doActionMethod();
	}
	public String getCancelEventsMethod(){
		return "cancelEventsFor" + action.getMappingInfo().getJavaName();
	}
	public boolean targetIsImplicitObject(){
		if(action instanceof IActionWithTargetElement){
			if(action.getInPartition() != null){
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
				if(action.getInPartition() != null && action.getInPartition().getRepresents() instanceof INakedProperty){
					targetMap = OJUtil.buildStructuralFeatureMap((INakedProperty) action.getInPartition().getRepresents());
				}else if(action instanceof IActionWithTargetPin && ((IActionWithTargetPin) action).getTarget() != null){
					targetMap = OJUtil.buildStructuralFeatureMap(action.getActivity(), ((IActionWithTargetPin) action).getTarget());
				}
			}
		}
		return targetMap;
	}
	public String targetName(){
		if(targetIsImplicitObject()){
			return implicitObject();
		}else{
			return "tgt" + action.getMappingInfo().getJavaName().getCapped();
		}
	}
	private INakedClassifier getExpectedTargetType(){
		if(action instanceof IActionWithTargetPin){
			return ((IActionWithTargetPin) action).getExpectedTargetType();
		}else if(action instanceof INakedCallBehaviorAction){
			return action.getActivity().getContext();
		}else{
			return null;
		}
	}
	private String implicitObject(){
		if(getExpectedTargetType() != null){
			if(BehaviorUtil.hasExecutionInstance(action.getActivity())){
				if(action.getActivity().getContext() != null && action.getActivity().getContext().conformsTo(getExpectedTargetType())){
					return "getContextObject()";
				}
			}
		}
		return "this";
	}
	void setAction(IActionWithTargetElement action){
		this.action = action;
	}
}