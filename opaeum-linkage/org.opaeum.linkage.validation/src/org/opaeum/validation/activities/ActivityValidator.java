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
import org.opaeum.metamodel.activities.INakedObjectFlow;
import org.opaeum.metamodel.activities.INakedObjectNode;
import org.opaeum.metamodel.bpm.INakedEmbeddedTask;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedParameter;
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
				}else if(node instanceof INakedObjectNode){
					INakedObjectNode object = (INakedObjectNode) node;
					validateFlows(object);
				}
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	protected void validateControlNode(INakedControlNode control){
		if(control.getControlNodeType().isJoinNode()){
			for(INakedActivityEdge e:control.getAllEffectiveIncoming()){
				visitEdge(e);
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitEdge(INakedActivityEdge e){
		INakedActivityNode es = e.getEffectiveTarget();
		if((es.isImplicitJoin() || (es instanceof INakedControlNode && ((INakedControlNode) es).getControlNodeType().isJoinNode())) && e.hasGuard()){
			getErrorMap().putError(e, ActivityValidationRule.NO_CONDITIONAL_FLOW_TO_JOIN, es.isImplicitJoin() ? "implicit" : "explicit", e.getEffectiveTarget());
		}
		if(e instanceof INakedObjectFlow){
			INakedObjectFlow of = (INakedObjectFlow) e;
			INakedObjectNode target = null;
			INakedObjectNode source = null;
			if(of.getTarget() instanceof INakedObjectNode){
				target = (INakedObjectNode) of.getTarget();
				source = target.getFeedingNode();
			}else if(of.getSource() instanceof INakedObjectNode){
				source = (INakedObjectNode) of.getSource();
				target = source.getFedNode();
			}
			if(source != null && target != null){
				if(of.getTransformation() == null){
					if(!target.getNakedBaseType().conformsTo(source.getNakedBaseType())){
						getErrorMap().putError(e, ActivityValidationRule.OBJECT_NODE_TYPES_MUST_MATCH, source, target);
					}
					if(of.getSelection() == null){
						if(!source.canDeliverOutputTo(target)){
							getErrorMap().putError(e, ActivityValidationRule.SOURCE_AND_TARGET_MULTIPLICTY_MUST_CORRESPOND, source, target);
						}
					}else{
						INakedParameter returnParam = of.getSelection().getReturnParameter();
						if(returnParam == null || of.getSelection().getArgumentParameters().size() != 1){
							getErrorMap().putError(e, ActivityValidationRule.SELECTION_REQUIRES_EXACTLY_ONE_IN_ONE_OUT, of.getSelection());
						}else{
							if(!returnParam.getNakedBaseType().conformsTo(target.getNakedBaseType())){
								getErrorMap().putError(e, ActivityValidationRule.SELECTION_RESULT_MUST_CONFORM_TO_TARGET_TYPE, returnParam, of.getSelection(), target);
							}else{
								INakedParameter arg1 = of.getSelection().getArgumentParameters().get(0);
								if(!arg1.getNakedBaseType().conformsTo(source.getNakedBaseType())){
									getErrorMap().putError(e, ActivityValidationRule.SELECTION_INPUT_MUST_CONFORM_TO_SOURCE_TYPE,
											of.getSelection().getArgumentParameters().get(0), of.getSelection(), source);
								}else if(!source.canDeliverOutputTo(arg1)){
									getErrorMap().putError(e, ActivityValidationRule.SELECTION_INPUT_MULTIPLICITY_MUST_CORRESPOND_WITH_TO_SOURCE_MULTIPLICITY,
											of.getSelection().getArgumentParameters().get(0), of.getSelection(), source);
								}else if(!target.canAcceptInputFrom(returnParam)){
									getErrorMap().putError(e, ActivityValidationRule.SELECTION_RESULT_MULTIPLICITY_MUST_CORRESPOND_WITH_TO_TARGET_MULTIPLICITY,
											returnParam, of.getSelection(), target);
								}else if(source.getNakedMultiplicity().getUpper() == 1 || source.getNakedMultiplicity().getUpper() == 0){
									getErrorMap().putError(e, ActivityValidationRule.SELECTION_BEHAVIOR_ASSUMES_MULTIPLE_SOURCE_VALUES, source, of.getSelection());
								}
							}
						}
					}
				}else if(of.getTransformation().getReturnParameter() == null || of.getTransformation().getArgumentParameters().size() != 1){
					getErrorMap().putError(e, ActivityValidationRule.TRANSFORMATION_REQUIRES_EXACTLY_ONE_IN_ONE_OUT, of.getTransformation());
				}else if(!of.getTransformation().getReturnParameter().getNakedBaseType().conformsTo(target.getNakedBaseType())){
					getErrorMap().putError(e, ActivityValidationRule.TRANSFORMATION_RESULT_MUST_CONFORM_TO_TARGET_TYPE, of.getTransformation().getReturnParameter(),
							of.getTransformation(), target);
				}else if(!of.getTransformation().getArgumentParameters().get(0).getNakedBaseType().conformsTo(source.getNakedBaseType())){
					getErrorMap().putError(e, ActivityValidationRule.TRANSFORMATION_INPUT_MUST_CONFORM_TO_SOURCE_TYPE, source, target);
				}else if(!target.canAcceptInputFrom(of.getTransformation().getReturnParameter())){
					getErrorMap().putError(e, ActivityValidationRule.TRANSFORMATION_RESULT_MULTIPLICITY_MUST_CORRESPOND_WITH_TO_TARGET_MULTIPLICITY,
							of.getTransformation().getReturnParameter(), of.getTransformation(), target);
				}else if(of.getTransformation().getArgumentParameters().get(0).getNakedMultiplicity().getUpper() != 1){
					getErrorMap().putError(e, ActivityValidationRule.TRANSFORMATION_ARGUMENT_MULTIPLICITY_MUST_BE_ONE,
							of.getTransformation().getArgumentParameters().get(0), of.getTransformation());
				}else if(!of.getTransformation().getArgumentParameters().get(0).getNakedBaseType().conformsTo(source.getNakedBaseType())){
					getErrorMap().putError(e, ActivityValidationRule.TRANSFORMATION_INPUT_MUST_CONFORM_TO_SOURCE_TYPE, source, target);
				}else if(of.getSelection() != null){
					INakedParameter returnParam = of.getSelection().getReturnParameter();
					if(returnParam == null || of.getSelection().getArgumentParameters().size() != 1){
						getErrorMap().putError(e, ActivityValidationRule.SELECTION_REQUIRES_EXACTLY_ONE_IN_ONE_OUT, of.getSelection());
					}else{
						if(!returnParam.getNakedBaseType().conformsTo(of.getTransformation().getReturnParameter().getNakedBaseType())){
							getErrorMap().putError(e, ActivityValidationRule.SELECTION_RESULT_MUST_CONFORM_TO_TARGET_TYPE, returnParam, of.getSelection(), target);
						}else{
							INakedParameter input = of.getSelection().getArgumentParameters().get(0);
							if(!input.getNakedBaseType().conformsTo(of.getTransformation().getReturnParameter().getNakedBaseType())){
								getErrorMap().putError(e, ActivityValidationRule.SELECTION_INPUT_MUST_CONFORM_TO_TRANSFORMATION_RESULT_TYPE,
										of.getSelection().getArgumentParameters().get(0), of.getSelection(), of.getTransformation().getReturnParameter(),
										of.getTransformation());
							}else if(!of.getTransformation().getReturnParameter().fitsInTo(input)){
								getErrorMap().putError(e, ActivityValidationRule.SELECTION_INPUT_MULTIPLICITY_MUST_CORRESPOND_WITH_TRANSFORMATION_RESULT_MULTIPLICITY,
										of.getSelection().getArgumentParameters().get(0), of.getSelection(), of.getTransformation().getReturnParameter(),
										of.getTransformation());
							}else if(!target.canAcceptInputFrom(returnParam)){
								getErrorMap().putError(e, ActivityValidationRule.SELECTION_RESULT_MULTIPLICITY_MUST_CORRESPOND_WITH_TO_TARGET_MULTIPLICITY, returnParam,
										of.getSelection(), target);
							}
						}
					}
				}
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
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
		if(action.getDefaultOutgoing().isEmpty() && action.getConditionalOutgoing().size() > 0){
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
