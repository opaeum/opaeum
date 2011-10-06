package org.opaeum.validation.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.linkage.ActivityValidationRule;
import org.opaeum.metamodel.actions.INakedAcceptEventAction;
import org.opaeum.metamodel.actions.INakedCallAction;
import org.opaeum.metamodel.activities.INakedAction;
import org.opaeum.metamodel.activities.INakedActivity;
import org.opaeum.metamodel.activities.INakedActivityEdge;
import org.opaeum.metamodel.activities.INakedActivityNode;
import org.opaeum.metamodel.activities.INakedControlNode;
import org.opaeum.metamodel.activities.INakedObjectNode;
import org.opaeum.metamodel.bpm.INakedEmbeddedTask;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.INakedValueSpecification;
import org.opaeum.validation.AbstractValidator;
import org.opaeum.validation.ValidationPhase;

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
				}else if(node instanceof INakedControlNode){
				}else if(node instanceof INakedObjectNode){
					INakedObjectNode object = (INakedObjectNode) node;
					validateFlows(object);
				}
			}
		}
	}
	@VisitBefore(matchSubclasses=true)
	protected void validateControlNode(INakedControlNode control){
		if(control.getControlNodeType().isJoinNode()){
			for(INakedActivityEdge e:control.getAllEffectiveIncoming()){
				visitEdge(e);
			}
		}
	}
	@VisitBefore(matchSubclasses=true)
	public void visitEdge(INakedActivityEdge e){
		INakedActivityNode es = e.getEffectiveTarget();
		if((es.isImplicitJoin() || (es instanceof INakedControlNode  && ((INakedControlNode) es).getControlNodeType().isJoinNode())) && e.hasGuard()){
			getErrorMap().putError(e, ActivityValidationRule.NO_CONDITIONAL_FLOW_TO_JOIN, es.isImplicitJoin()?"implicit":"explicit", e.getEffectiveTarget());
		}
	}
	@VisitBefore(matchSubclasses=true)
	public void validateAction(INakedAction action){
		validateFlows(action);
		if(!action.getActivity().isProcess()){
			checkDisallowedActions(action);
		}
	}
	private void validateFlows(INakedObjectNode object){
		if(object.getAllEffectiveIncoming().size() > 1){
			getErrorMap().putError(object, ActivityValidationRule.ONE_FLOW_TO_OBJECT_NODES);
		}else if(object.getAllEffectiveOutgoing().size() > 1){
			getErrorMap().putError(object, ActivityValidationRule.ONE_FLOW_FROM_OBJECT_NODES);
		}
	}
	private void checkDisallowedActions(INakedAction action){
		if(action instanceof INakedEmbeddedTask){
			getErrorMap().putError(action, ActivityValidationRule.LONG_RUNNING_ACTIONS_ONLY_IN_PROCESS, action.getActivity());
		}else if(action instanceof INakedAcceptEventAction){
			getErrorMap().putError(action, ActivityValidationRule.ACCEPT_EVENT_ACTION_ONLY_IN_PROCESS, action.getActivity());
		}else if(action instanceof INakedCallAction){
			INakedCallAction callAction = (INakedCallAction) action;
			if(callAction.isLongRunning()){
				getErrorMap().putError(action, ActivityValidationRule.LONG_RUNNING_ACTIONS_ONLY_IN_PROCESS);
			}else if(callAction.isLongRunning()){
				getErrorMap().putError(action, ActivityValidationRule.LONG_RUNNING_ACTIONS_ONLY_IN_PROCESS);
			}
		}
	}
	private void validateFlows(INakedAction action){
		if(action.getDefaultOutgoing().isEmpty() && action.getConditionalOutgoing().size()>0){
			getErrorMap().putError(action, ActivityValidationRule.AT_LEAST_ONE_DEFAULT_FLOW, "No default flows found");
		}else if(action.isImplicitJoin()){
			for(INakedActivityEdge e:action.getAllEffectiveIncoming()){
				if(e.hasGuard()){
					getErrorMap().putError(e, ActivityValidationRule.NO_CONDITIONAL_FLOW_TO_JOIN, "implicit", e.getEffectiveTarget());
				}
			}
		}
	}
	protected Map<String,List<INakedValueSpecification>> getGuards(INakedActivity activity){
		Map<String,List<INakedValueSpecification>> results = new HashMap<String,List<INakedValueSpecification>>();
		for(INakedActivityEdge e:activity.getActivityEdges()){
			if(e.hasGuard()){
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
