package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.IActionWithTarget;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedProperty;

public class ActionMap {
	IActionWithTarget action;
	NakedStructuralFeatureMap targetMap;

	public ActionMap(IActionWithTarget action) {
		super();
		this.action = action;
	}

	public boolean targetIsImplicitObject() {
		if (action.getInPartition() != null) {
			return false;
		} else if (action.getTarget()!=null && action.getTarget().hasValidInput()) {
			return false;
		} else {
			return true;
		}
	}

	public NakedStructuralFeatureMap targetMap() {
		if (targetMap == null) {
			if (this.action.getInPartition() != null && this.action.getInPartition().getRepresents() instanceof INakedProperty) {
				targetMap = OJUtil.buildStructuralFeatureMap((INakedProperty) this.action.getInPartition().getRepresents());
			} else if (this.action.getTargetElement() != null) {
				targetMap = OJUtil.buildStructuralFeatureMap(this.action.getActivity(), this.action.getTargetElement());
			}
		}
		return targetMap;
	}

	public String targetName() {
		if (targetIsImplicitObject()) {
			return implicitObject();
		} else {
			return "tgt" + action.getMappingInfo().getJavaName().getCapped();
		}
	}

	public String implicitObject() {
		if (action.getExpectedTargetType() != null) {
			if (BehaviorUtil.hasExecutionInstance(action.getActivity())) {
				if (action.getActivity().getContext() != null
						&& action.getActivity().getContext().conformsTo(action.getExpectedTargetType())) {
					return "getContextObject()";
				}
			}
		}
		return "this";
	}

	public IActionWithTarget getAction() {
		return action;
	}

	public INakedClassifier targetBaseType() {
		if (targetIsImplicitObject()) {
			if (action.getExpectedTargetType() != null) {
				if (BehaviorUtil.hasExecutionInstance(action.getActivity())) {
					if (action.getContext() != null && action.getContext().conformsTo(action.getExpectedTargetType())) {
						return action.getContext();
					}
				}
			}
			return action.getActivity();
		} else {
			return ((INakedProperty) targetMap().getFeature()).getNakedBaseType();
		}
	}
}