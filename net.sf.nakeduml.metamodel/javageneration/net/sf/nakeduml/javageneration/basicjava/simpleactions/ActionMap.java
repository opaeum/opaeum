package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.IActionWithTarget;
import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedProperty;

public class ActionMap extends ActivityNodeMap {
	private INakedAction action;
	NakedStructuralFeatureMap targetMap;

	public ActionMap(INakedAction action) {
		super(action);
		this.action=action;
	}

	public String getFireTimersMethod() {
		return super.doActionMethod();
	}

	public String getCancelTimersMethod() {
		return "cancelTimersFor" + action.getMappingInfo().getJavaName();
	}
	public boolean targetIsImplicitObject() {
		if (getActionWithTarget().getInPartition() != null) {
			return false;
		} else if (getActionWithTarget().getTarget() != null && getActionWithTarget().getTarget().hasValidInput()) {
			return false;
		} else {
			return true;
		}
	}

	public NakedStructuralFeatureMap targetMap() {
		if (targetMap == null) {
			if (this.getActionWithTarget().getInPartition() != null
					&& this.getActionWithTarget().getInPartition().getRepresents() instanceof INakedProperty) {
				targetMap = OJUtil.buildStructuralFeatureMap((INakedProperty) this.getActionWithTarget().getInPartition().getRepresents());
			} else if (this.getActionWithTarget().getTargetElement() != null) {
				targetMap = OJUtil.buildStructuralFeatureMap(this.getActionWithTarget().getActivity(), this.getActionWithTarget()
						.getTargetElement());
			}
		}
		return targetMap;
	}

	public String targetName() {
		if (targetIsImplicitObject()) {
			return implicitObject();
		} else {
			return "tgt" + getActionWithTarget().getMappingInfo().getJavaName().getCapped();
		}
	}

	public String implicitObject() {
		if (getActionWithTarget().getExpectedTargetType() != null) {
			if (BehaviorUtil.hasExecutionInstance(getActionWithTarget().getActivity())) {
				if (getActionWithTarget().getActivity().getContext() != null
						&& getActionWithTarget().getActivity().getContext().conformsTo(getActionWithTarget().getExpectedTargetType())) {
					return "getContextObject()";
				}
			}
		}
		return "this";
	}

	public IActionWithTarget getActionWithTarget() {
		if (action instanceof IActionWithTarget) {
			return (IActionWithTarget) action;
		} else {
			return null;
		}
	}

	public INakedClassifier targetBaseType() {
		if (targetIsImplicitObject()) {
			if (getActionWithTarget().getExpectedTargetType() != null) {
				if (BehaviorUtil.hasExecutionInstance(getActionWithTarget().getActivity())) {
					if (getActionWithTarget().getContext() != null
							&& getActionWithTarget().getContext().conformsTo(getActionWithTarget().getExpectedTargetType())) {
						return getActionWithTarget().getContext();
					}
				}
			}
			return getActionWithTarget().getActivity();
		} else {
			return ((INakedProperty) targetMap().getFeature()).getNakedBaseType();
		}
	}

	void setAction(IActionWithTarget action) {
		this.action = action;
	}

}