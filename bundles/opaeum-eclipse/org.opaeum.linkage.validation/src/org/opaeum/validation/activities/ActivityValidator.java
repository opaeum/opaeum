package org.opaeum.validation.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.uml2.uml.AcceptEventAction;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.ControlNode;
import org.eclipse.uml2.uml.JoinNode;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.ObjectFlow;
import org.eclipse.uml2.uml.ObjectNode;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.ValueSpecification;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.linkage.ActivityValidationRule;
import org.opaeum.validation.AbstractValidator;
import org.opaeum.validation.ValidationPhase;

@StepDependency(phase = ValidationPhase.class)
public class ActivityValidator extends AbstractValidator{
	@VisitBefore(matchSubclasses = true)
	public void class_Before(Classifier c){
		if(c instanceof Activity){
			// TODO validate that, if both a context AND a composite are
			// present, that they are of the same type
			Activity activity = (Activity) c;
			for(ActivityNode node:EmfActivityUtil.getActivityNodesRecursively(activity)){
				if(node instanceof Action){
				}else if(node instanceof ObjectNode){
					ObjectNode object = (ObjectNode) node;
					validateFlows(object);
				}
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	protected void validateControlNode(ControlNode control){
		if(control instanceof JoinNode){
			for(ActivityEdge e:EmfActivityUtil.getAllEffectiveIncoming(control)){
				visitEdge(e);
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitEdge(ActivityEdge e){
		//NB!! Doesn't matter much, but let's assume selection gets executed before transformation
		// TODO validate edges,transformations and selections recursively until an object node is found. Remember to start from one side,
		// say the start, and not to evaluate the same flow multiple times
		ActivityNode es = EmfActivityUtil.getEffectiveTarget(e);
		if((EmfActivityUtil.isImplicitJoin(es) || (es instanceof JoinNode)) && EmfActivityUtil.hasGuard(e)){
			getErrorMap().putError(e, ActivityValidationRule.NO_CONDITIONAL_FLOW_TO_JOIN, EmfActivityUtil.isImplicitJoin(es) ? "implicit" : "explicit",
					EmfActivityUtil.getEffectiveTarget(e));
		}
		if(e instanceof ObjectFlow){
			ObjectFlow of = (ObjectFlow) e;
			ObjectNode target = null;
			ObjectNode source = null;
			if(of.getTarget() instanceof ObjectNode){
				target = (ObjectNode) of.getTarget();
				source = EmfActivityUtil.getFeedingNode(target);
			}else if(of.getSource() instanceof ObjectNode){
				source = (ObjectNode) of.getSource();
				target = EmfActivityUtil.getFedNode(source);
			}
			if(source != null && target != null){
				Type targetType = EmfActionUtil.calculateType(target);
				Type sourceType = EmfActionUtil.calculateType(source);
				if(of.getTransformation() == null){
					if(!targetType.conformsTo(sourceType)){
						getErrorMap().putError(e, ActivityValidationRule.OBJECT_NODE_TYPES_MUST_MATCH, source, target);
					}
					if(of.getSelection() == null){
						if(!canDeliverOutputToObjectNode(source, target)){
							getErrorMap().putError(e, ActivityValidationRule.SOURCE_AND_TARGET_MULTIPLICTY_MUST_CORRESPOND, source, target);
						}
					}else{
						validateSelection(e, of, target, targetType, sourceType);
					}
				}else{
					Parameter txResult = EmfBehaviorUtil.getReturnParameter(of.getTransformation());
					if(txResult == null || EmfBehaviorUtil.getArgumentParameters(of.getTransformation()).size() != 1){
						getErrorMap().putError(e, ActivityValidationRule.TRANSFORMATION_REQUIRES_EXACTLY_ONE_IN_ONE_OUT, of.getTransformation());
					}else if(txResult.getType() != null && !txResult.getType().conformsTo(targetType)){
						getErrorMap().putError(e, ActivityValidationRule.TRANSFORMATION_RESULT_MUST_CONFORM_TO_TARGET_TYPE, txResult, of.getTransformation(), target);
					}else if(!EmfBehaviorUtil.getArgumentParameters(of.getTransformation()).get(0).getType().conformsTo(sourceType)){
						getErrorMap().putError(e, ActivityValidationRule.TRANSFORMATION_INPUT_MUST_CONFORM_TO_SOURCE_TYPE, source, target);
					}else if(!canReadDataFrom(target, txResult)){
						getErrorMap().putError(e, ActivityValidationRule.TRANSFORMATION_RESULT_MULTIPLICITY_MUST_CORRESPOND_WITH_TO_TARGET_MULTIPLICITY, txResult,
								of.getTransformation(), target);
					}else if(EmfBehaviorUtil.getArgumentParameters(of.getTransformation()).get(0).isMultivalued()){
						getErrorMap().putError(e, ActivityValidationRule.TRANSFORMATION_ARGUMENT_MULTIPLICITY_MUST_BE_ONE,
								EmfBehaviorUtil.getArgumentParameters(of.getTransformation()).get(0), of.getTransformation());
					}else if(!EmfBehaviorUtil.getArgumentParameters(of.getTransformation()).get(0).getType().conformsTo(sourceType)){
						getErrorMap().putError(e, ActivityValidationRule.TRANSFORMATION_INPUT_MUST_CONFORM_TO_SOURCE_TYPE, source, target);
					}else if(of.getSelection() != null){
						validateSelection(e, of, target, targetType, sourceType);
					}
				}
			}
		}
	}
	private void validateSelection(ActivityEdge e,ObjectFlow of,ObjectNode target,Type targetType,Type sourceType){
		Parameter returnParam = EmfBehaviorUtil.getReturnParameter(of.getSelection());
		if(!(of.getSource() instanceof ObjectNode)){
			getErrorMap().putError(e, ActivityValidationRule.SELECTION_REQUIRES_OBJECT_SOURCE_NODE, of.getSelection());
		}

		if(returnParam == null || EmfBehaviorUtil.getArgumentParameters(of.getSelection()).size() != 1){
			getErrorMap().putError(e, ActivityValidationRule.SELECTION_REQUIRES_EXACTLY_ONE_IN_ONE_OUT, of.getSelection());
		}else{
			if(returnParam.getType() != null && !returnParam.getType().conformsTo(targetType)){
				getErrorMap().putError(e, ActivityValidationRule.SELECTION_RESULT_MUST_CONFORM_TO_TARGET_TYPE, returnParam, of.getSelection(), target);
			}else{
				Parameter arg1 = EmfBehaviorUtil.getArgumentParameters(of.getSelection()).get(0);
				if(arg1.getType() != null && of.getSource() instanceof ObjectNode){
					ObjectNode selectionSource=(ObjectNode) of.getSource();
					if(!arg1.getType().conformsTo(sourceType)){
						getErrorMap().putError(e, ActivityValidationRule.SELECTION_INPUT_MUST_CONFORM_TO_SOURCE_TYPE,
								arg1, of.getSelection(), selectionSource);
					}
					if(!arg1.isMultivalued()){
						getErrorMap().putError(e, ActivityValidationRule.SELECTION_INPUT_MULTIPLICITY_MUST_BE_BAG,
								arg1, of.getSelection(), selectionSource);
					}
					if(returnParam.isMultivalued()){
						getErrorMap().putError(e, ActivityValidationRule.SELECTION_RESULT_MULTIPLICITY_MUST_BE_SINGLE_VALUED, returnParam,
								of.getSelection(), target);
					}
					if(!EmfActivityUtil.isMultivalued(selectionSource)){
						getErrorMap().putError(e, ActivityValidationRule.SELECTION_BEHAVIOR_ASSUMES_MULTIPLE_SOURCE_VALUES, selectionSource, of.getSelection());
					}
				}
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void validateAction(Action action){
		validateFlows(action);
		if(!EmfBehaviorUtil.isProcess(EmfActivityUtil.getContainingActivity(action))){
			checkDisallowedActions(action);
		}
	}
	private void validateFlows(ObjectNode object){
		if(EmfActivityUtil.getAllEffectiveIncoming(object).size() > 1){
			getErrorMap().putError(object, ActivityValidationRule.ONE_FLOW_TO_OBJECT_NODES);
		}else if(EmfActivityUtil.getAllEffectiveOutgoing(object).size() > 1){
			getErrorMap().putError(object, ActivityValidationRule.ONE_FLOW_FROM_OBJECT_NODES);
		}
	}
	private void checkDisallowedActions(Action action){
		if(EmfActionUtil.isEmbeddedTask(action)){
			getErrorMap().putError(action, ActivityValidationRule.LONG_RUNNING_ACTIONS_ONLY_IN_PROCESS, action.getActivity());
		}else if(action instanceof AcceptEventAction){
			getErrorMap().putError(action, ActivityValidationRule.ACCEPT_EVENT_ACTION_ONLY_IN_PROCESS, action.getActivity());
		}else if(action instanceof CallBehaviorAction){
			CallBehaviorAction callAction = (CallBehaviorAction) action;
			if(callAction.getBehavior() != null && EmfBehaviorUtil.isLongRunning(callAction.getBehavior())){
				getErrorMap().putError(action, ActivityValidationRule.LONG_RUNNING_ACTIONS_ONLY_IN_PROCESS);
			}
		}else if(action instanceof CallOperationAction){
			CallOperationAction callAction = (CallOperationAction) action;
			if(callAction.getOperation() != null && EmfBehaviorUtil.isLongRunning(callAction.getOperation())){
				getErrorMap().putError(action, ActivityValidationRule.LONG_RUNNING_ACTIONS_ONLY_IN_PROCESS);
			}
		}
	}
	private void validateFlows(Action action){
		if(EmfActivityUtil.getDefaultOutgoing(action).isEmpty() && EmfActivityUtil.getConditionalOutgoing(action).size() > 0){
			getErrorMap().putError(action, ActivityValidationRule.AT_LEAST_ONE_DEFAULT_FLOW, "No default flows found");
		}else if(EmfActivityUtil.isImplicitJoin(action)){
			for(ActivityEdge e:EmfActivityUtil.getAllEffectiveIncoming(action)){
				if(EmfActivityUtil.hasGuard(e)){
					getErrorMap().putError(e, ActivityValidationRule.NO_CONDITIONAL_FLOW_TO_JOIN, "implicit", EmfActivityUtil.getEffectiveTarget(e));
				}
			}
		}
	}
	protected Map<String,List<ValueSpecification>> getGuards(Activity activity){
		Map<String,List<ValueSpecification>> results = new HashMap<String,List<ValueSpecification>>();
		for(ActivityEdge e:activity.getEdges()){
			if(EmfActivityUtil.hasGuard(e)){
				addNamedElement(results, e.getGuard());
			}
		}
		return results;
	}
	protected Map<String,List<Property>> getEmulatedAttributes(Activity activity){
		Map<String,List<Property>> results = new HashMap<String,List<Property>>();
		for(Property e:EmfPropertyUtil.getEffectiveProperties(activity)){
			addNamedElement(results, e);
		}
		return results;
	}
	protected Map<String,List<Action>> getActions(Activity activity){
		Map<String,List<Action>> results = new HashMap<String,List<Action>>();
		for(ActivityNode e:EmfActivityUtil.getActivityNodesRecursively(activity)){
			if(e instanceof Action){
				addNamedElement(results, (Action) e);
			}
		}
		return results;
	}
	private <E extends NamedElement>void addNamedElement(Map<String,List<E>> results,E e){
		List<E> guards = results.get(e.getName());
		if(guards == null){
			guards = new ArrayList<E>();
			results.put(e.getName(), guards);
		}
		guards.add(e);
	}
}
