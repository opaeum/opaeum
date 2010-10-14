package net.sf.nakeduml.validation.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.IActionWithTarget;
import net.sf.nakeduml.metamodel.actions.INakedAcceptEventAction;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.actions.INakedOpaqueAction;
import net.sf.nakeduml.metamodel.actions.INakedSendObjectAction;
import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.activities.INakedActivityEdge;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.activities.INakedControlNode;
import net.sf.nakeduml.metamodel.activities.INakedObjectNode;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.validation.AbstractValidator;
import net.sf.nakeduml.validation.ValidationPhase;

@StepDependency(phase = ValidationPhase.class)
public class ActivityValidator extends AbstractValidator{
	@VisitBefore(matchSubclasses = true)
	public void class_Before(INakedClassifier c){
		if(c instanceof INakedActivity){
			// TODO validate that, if both a context AND a composite are
			// present, that they are of the same type
			INakedActivity activity = (INakedActivity) c;
			for(INakedActivityNode node:activity.getActivityNodesRecursively()){
				if(node instanceof INakedAction){
					INakedAction action = (INakedAction) node;
					validateFlows(action);
					if(!activity.isProcess()){
						checkDisallowedActions(action);
					}
					if(action instanceof IActionWithTarget){
						IActionWithTarget actionWithTarget = (IActionWithTarget) action;
						validateTargets(actionWithTarget);
					}
				}else if(node instanceof INakedControlNode){
					INakedControlNode control = (INakedControlNode) node;
					if(control.getControlNodeType().isJoinNode()){
						for(INakedActivityEdge e:node.getAllEffectiveIncoming()){
							if(e.getGuard() != null){
								getErrorMap().putError(e, ActivityValidationRule.NO_CONDITIONAL_FLOW_TO_JOIN, "Flows to joins cannot have conditions");
							}
						}
					}
				}else if(node instanceof INakedObjectNode){
					INakedObjectNode object = (INakedObjectNode) node;
					validateFlows(object);
				}
			}
			if(!activity.getActivityKind().isSimpleSynchronousMethod()){
				validateClashingNames(getActions(activity), "Action names may not clash", ActivityValidationRule.UNIQUE_ACTION_NAMES);
				validateClashingNames(getEmulatedAttributes(activity), "The names of structural aspects processes may not clash",
						ActivityValidationRule.UNIQUE_EMULATED_ATTRIBUTES);
				if(activity.isProcess()){
					validateClashingNames(getGuards(activity), "Guard names of activity edges my not clash",
							ActivityValidationRule.UNIQUE_GUARD_NAMES);
				}
			}
		}
	}
	private void validateTargets(IActionWithTarget actionWithTarget){
		// TODO check target types with expected classifier
		if(actionWithTarget.getTargetElement() == null){
			// TODO check if target type represents user
			if(actionWithTarget instanceof INakedOpaqueAction){
				getErrorMap().putError(actionWithTarget, ActivityValidationRule.TARGET_FOR_OPAQUE_ACTIONS,
						"Opaque action does not have an input marked as target)");
			}
		}
	}
	private <E extends INakedElement>void validateClashingNames(Map<String,List<E>> guards,String message,ActivityValidationRule rule){
		for(Map.Entry<String,List<E>> entry:guards.entrySet()){
			if(entry.getValue().size() >= 2){
				for(E e:entry.getValue()){
					getErrorMap().putError(e, rule, message);
				}
			}
		}
	}
	private void validateFlows(INakedObjectNode object){
		if(object.getAllEffectiveIncoming().size() > 1){
			getErrorMap().putError(object, ActivityValidationRule.ONE_FLOW_TO_OBJECT_NODES, "Multiple outgoing flows found to object node");
		}else if(object.getAllEffectiveOutgoing().size() > 1){
			getErrorMap().putError(object, ActivityValidationRule.ONE_FLOW_FROM_OBJECT_NODES, "Multiple outgoing flows found from object node");
		}
	}
	private void checkDisallowedActions(INakedAction action){
		if(action instanceof INakedSendObjectAction){
			getErrorMap().putError(action, ActivityValidationRule.SEND_OBJECT_ACTION_ONLY_IN_PROCESS,
					"SendObjectActions can only be used in activities marked as a processes");
		}else if(action instanceof INakedOpaqueAction){
			getErrorMap().putError(action, ActivityValidationRule.OPAQUE_ACTION_ONLY_IN_PROCESS,
					"OpaqueActions can only be used in activities marked as a processes");
		}else if(action instanceof INakedAcceptEventAction){
			getErrorMap().putError(action, ActivityValidationRule.ACCEPT_EVENT_ACTION_ONLY_IN_PROCESS,
					"AcceptEventActions can only be used in activities marked as a processes");
		}else if(action instanceof INakedCallAction){
			INakedCallAction callACtion = (INakedCallAction) action;
			if(callACtion.getCalledElement().isProcess()){
				getErrorMap().putError(action, ActivityValidationRule.PROCESS_INVOCATION_ONLY_IN_PROCESS,
						"Processes can only be invoked from activities marked as a processes");
			}else if(BehaviorUtil.isUserTask(callACtion)){
				getErrorMap().putError(action, ActivityValidationRule.RESPONSIBILITIY_INVOCATION_ONLY_IN_PROCESS,
						"User Responsibiliteis can only be invoked from activities marked as a processes");
			}
		}
	}
	private void validateFlows(INakedAction action){
		if(action.getDefaultOutgoing().isEmpty()){
			getErrorMap().putError(action, ActivityValidationRule.AT_LEAST_ONE_DEFAULT_FLOW, "No default flows found");
		}else if(action.getDefaultOutgoing().size() > 1 && action.getConditionalOutgoing().size() > 1){
			getErrorMap().putError(action, ActivityValidationRule.IMPLICIT_FORK_OR_DECISION, "Multiple default and conditional flows found");
		}else if(action.isImplicitJoin()){
			for(INakedActivityEdge e:action.getAllEffectiveIncoming()){
				if(e.getGuard() != null){
					getErrorMap().putError(e, ActivityValidationRule.NO_CONDITIONAL_FLOW_TO_JOIN, "Flows to implicit joins cannot have conditions");
				}
			}
		}
	}
	protected Map<String,List<INakedValueSpecification>> getGuards(INakedActivity activity){
		Map<String,List<INakedValueSpecification>> results = new HashMap<String,List<INakedValueSpecification>>();
		for(INakedActivityEdge e:activity.getActivityEdges()){
			if(e.getGuard() != null){
				addNamedElement(results, e.getGuard());
			}
		}
		return results;
	}
	protected Map<String,List<INakedProperty>> getEmulatedAttributes(INakedActivity activity){
		Map<String,List<INakedProperty>> results = new HashMap<String,List<INakedProperty>>();
		for(INakedProperty e:activity.getEffectiveAttributes()){
			addNamedElement(results, e);
		}
		return results;
	}
	protected Map<String,List<INakedAction>> getActions(INakedActivity activity){
		Map<String,List<INakedAction>> results = new HashMap<String,List<INakedAction>>();
		for(INakedActivityNode e:activity.getActivityNodesRecursively()){
			if(e instanceof INakedAction){
				addNamedElement(results, (INakedAction) e);
			}
		}
		return results;
	}
	private <E extends INakedElement>void addNamedElement(Map<String,List<E>> results,E e){
		List<E> guards = results.get(e.getName());
		if(guards == null){
			guards = new ArrayList<E>();
			results.put(e.getName(), guards);
		}
		guards.add(e);
	}
}
