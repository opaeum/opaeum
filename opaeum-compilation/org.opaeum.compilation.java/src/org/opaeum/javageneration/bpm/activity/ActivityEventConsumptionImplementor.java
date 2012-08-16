package org.opaeum.javageneration.bpm.activity;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.klasse.octopus.codegen.umlToJava.maps.OperationMap;
import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.AcceptCallAction;
import org.eclipse.uml2.uml.AcceptEventAction;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.OutputPin;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.uml.TypedElement;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfEventUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.OperationAnnotator;
import org.opaeum.javageneration.basicjava.SpecificationImplementor;
import org.opaeum.javageneration.bpm.AbstractEventConsumptionImplementor;
import org.opaeum.javageneration.bpm.ElementsWaitingForEvent;
import org.opaeum.javageneration.bpm.actions.Jbpm5ActionBuilder;
import org.opaeum.javageneration.bpm.actions.Jbpm5ObjectNodeExpressor;
import org.opaeum.metamodel.core.internal.StereotypeNames;

@StepDependency(phase = JavaTransformationPhase.class,requires = {ActivityProcessImplementor.class,SpecificationImplementor.class},after = {
		ActivityProcessImplementor.class,SpecificationImplementor.class})
public class ActivityEventConsumptionImplementor extends AbstractEventConsumptionImplementor{
	private Jbpm5ActionBuilder<ActivityNode> actionBuilder;
	@VisitBefore(matchSubclasses = true)
	public void visitActivity(Activity activity){
		if(EmfBehaviorUtil.isProcess(activity)){
			OJAnnotatedClass activityClass = findJavaClass(activity);
			super.implementEventConsumption(activityClass, activity, getEventActions(activity));
			for(ActivityNode n:EmfActivityUtil.getActivityNodesRecursively(activity)){
				if(n instanceof AcceptCallAction){
					AcceptCallAction acc = (AcceptCallAction) n;
					Operation no = EmfActionUtil.getOperation(acc);
					OperationMap map = ojUtil.buildOperationMap(no);
					OJAnnotatedOperation oper = operationAnnotator.findOrCreateOperation(activity, activityClass, map, true);
					OJBlock mainBlock = oper.getBody();
					if(EmfBehaviorUtil.hasExecutionInstance(no)){
						PropertyMap actionMap = ojUtil.buildStructuralFeatureMap(acc);
						final String EXECUTE_STATEMENT = "executeStatement";
						oper.getBody().removeFromStatements(oper.getBody().findStatementRecursive(EXECUTE_STATEMENT));
						mainBlock.addToStatements(actionMap.setter() + "(result)");
					}else{
						for(OutputPin pin:acc.getResults()){
							PropertyMap pinMap = ojUtil.buildStructuralFeatureMap(pin);
							PropertyMap parameterMap = ojUtil.buildStructuralFeatureMap(EmfActionUtil.getLinkedTypedElement(pin));
							mainBlock.addToStatements(pinMap.setter() + "(" + parameterMap.fieldname() + ")");
						}
					}
					if(!EmfBehaviorUtil.hasExecutionInstance(no)){
						for(InputPin pin:EmfActionUtil.getReplyAction(acc).getReplyValues()){
							if(((Parameter) EmfActionUtil.getLinkedTypedElement(pin)).getDirection() == ParameterDirectionKind.RETURN_LITERAL){
								Jbpm5ObjectNodeExpressor expressor = new Jbpm5ObjectNodeExpressor(ojUtil);
								mainBlock.addToStatements("result=" + expressor.expressInputPinOrOutParamOrExpansionNode(mainBlock, pin));
							}
						}
					}
					if(!activity.conformsTo((Classifier) no.getOwner()) && activity.getContext() != null
							&& activity.getContext().conformsTo((Classifier) no.getOwner())){
						// TODO find the right activity and delegate to it
					}
				}else if(EmfActionUtil.isEmbeddedTask(n)){
					Action t = (Action) n;
					String stereotypeName = n instanceof OpaqueAction ? StereotypeNames.EMBEDDED_SINGLE_SCREEN_TASK
							: StereotypeNames.EMBEDDED_SCREEN_FLOW_TASK;
					for(TimeEvent d:getLibrary().getResponsibilityDefinition(t, stereotypeName).getDeadlines()){
						OJPathName date = ojUtil.classifierPathname(getLibrary().getDateType());
						OJPathName task = ojUtil.classifierPathname(t);
						String consumerName = eventUtil.getEventConsumerName(d);
						OJOperation findOperation = activityClass.findOperation(consumerName, Arrays.asList(date, task));
						if(findOperation == null){
							findOperation = new OJAnnotatedOperation(consumerName, new OJPathName("boolean"));
							activityClass.addToOperations(findOperation);
							findOperation.addParam("date", date);
							findOperation.addParam("task", task);
							findOperation.getBody().addToStatements("return false");
						}
					}
				}
			}
		}
	}
	private OJBlock checkWeight(OJOperation operationContext,OJBlock block,ActivityNode node){
		StringBuilder sb = new StringBuilder();
		// Check if all weights have been satisfied
		// NB!! the weight logic only makes sense on AcceptEventActions. This is the only place where outputpin value count equates to
		// weight. Everywhere else it is impossible to determine weight. In other places it could also lead to stuck contractedProcesses
		// TODO implement validation
		for(ActivityEdge edge:EmfActivityUtil.getDefaultOutgoing(node)){
			if(edge.getSource() instanceof OutputPin){
				PropertyMap map = ojUtil.buildStructuralFeatureMap((OutputPin) edge.getSource());
				if(edge.getWeight() != null){
					if(map.isCollection()){
						Classifier integerType = getLibrary().getIntegerType();
						if(edge.getWeight() != null){
						}
						String weight = valueSpecificationUtil.expressValue(operationContext, edge.getWeight(),
								EmfActivityUtil.getContainingActivity(edge.getSource()), integerType);
						if(sb.length() > 0){
							sb.append(" && ");
						}
						sb.append(map.getter() + "().size()>=" + weight);
					}else{
						// would not make sense - ignore
					}
				}
			}
		}
		if(sb.length() > 0){
			OJIfStatement ifStatement = new OJIfStatement();
			block.addToStatements(ifStatement);
			ifStatement.setCondition(sb.toString());
			block = ifStatement.getThenPart();
		}
		return block;
	}
	private void storeArguments(OJIfStatement ifTokenFound,AcceptEventAction aea){
		List<OutputPin> result = aea.getResults();
		Jbpm5ObjectNodeExpressor expressor = new Jbpm5ObjectNodeExpressor(ojUtil);
		for(int i = 0;i < result.size();i++){
			OutputPin argument = result.get(i);
			PropertyMap pinMap = ojUtil.buildStructuralFeatureMap(argument);
			TypedElement parm = EmfActionUtil.getLinkedTypedElement(argument);
			if(parm == null){
				String param = "unknown";
				if(EmfActionUtil.acceptsDeadline(aea)){
					param = i == 1 ? "source.getTaskRequest()" : "triggerDate";
				}else if(EmfEventUtil.containsTriggerType(aea, TimeEvent.class)){
					param = "triggerDate";
				}
				ifTokenFound.getThenPart().addToStatements(expressor.storeResults(pinMap, param, false));
			}else{
				PropertyMap parmMap = ojUtil.buildStructuralFeatureMap(parm);
				String expression = parmMap.fieldname();
				if(parm instanceof Property){
					// signal
					expression = "signal." + parmMap.getter() + "()";
				}
				ifTokenFound.getThenPart().addToStatements(expressor.storeResults(pinMap, expression, parmMap.isMany()));
			}
		}
	}
	private Collection<ElementsWaitingForEvent> getEventActions(Activity activity){
		Map<Element,ElementsWaitingForEvent> results = new HashMap<Element,ElementsWaitingForEvent>();
		for(ActivityNode node:EmfActivityUtil.getActivityNodesRecursively(activity)){
			if(node instanceof AcceptEventAction && !(node instanceof AcceptCallAction)){
				AcceptEventAction action = (AcceptEventAction) node;
				for(Trigger trigger:action.getTriggers()){
					if(trigger.getEvent() != null){
						ElementsWaitingForEvent eventActions = results.get(trigger.getEvent());
						if(eventActions == null){
							eventActions = new ElementsWaitingForEvent(trigger.getEvent());
							results.put(trigger.getEvent(), eventActions);
						}
						eventActions.addWaitingNode(action);
					}
				}
			}
		}
		return results.values();
	}
	@Override
	public void release(){
		super.release();
		actionBuilder = null;
	}

	@Override
	protected void implementEventConsumer(Behavior behavior,OJAnnotatedClass ojBehavior,ElementsWaitingForEvent eventActions){
		OJAnnotatedOperation eventConsumer = super.createEventConsumerSignature(behavior, ojBehavior, eventActions.getEvent());
		OJForStatement forEachToken = new OJForStatement("token", ActivityProcessImplementor.ACTIVITY_TOKEN, "getTokens()");
		eventConsumer.getBody().addToStatements(forEachToken);
		for(NamedElement waitingNode:eventActions.getWaitingNodes()){
			AcceptEventAction action = (AcceptEventAction) waitingNode;
			OJPathName waitingClass = ojUtil.classifierPathname(action);
			ojBehavior.addToImports(waitingClass);
			if(EmfActivityUtil.getAllEffectiveIncoming(action).isEmpty()){
				// check if the activityNodeContainer of this action is active and create a new token
			}else{
				String condition = "result==false && token.isActive() && token.getCurrentExecutionElement() instanceof " + waitingClass.getName();
				OJIfStatement ifMatchFound = new OJIfStatement(condition);
				forEachToken.getBody().addToStatements(ifMatchFound);
				OJAnnotatedField stateActivation = new OJAnnotatedField("state", waitingClass);
				stateActivation.setInitExp("(" + waitingClass.getName() + ")token.getCurrentExecutionElement()");
				ifMatchFound.getThenPart().addToLocals(stateActivation);
				for(ActivityEdge transition:action.getOutgoings()){
					OJPathName tpn = ojUtil.classifierPathname(transition);
					OJIfStatement ifAccept = new OJIfStatement("result==false &&  get" + tpn.getLast() + "()."
							+ OperationAnnotator.delegateParameters(eventConsumer));
					ifMatchFound.getThenPart().addToStatements(ifAccept);
					ifAccept.getThenPart().addToStatements("result=true");
					ifAccept.getThenPart().addToStatements("break");
					break;
				}
			}
		}
	}
}
