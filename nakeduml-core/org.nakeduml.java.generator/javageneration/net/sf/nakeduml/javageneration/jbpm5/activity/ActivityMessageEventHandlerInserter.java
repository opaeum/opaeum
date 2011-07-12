package net.sf.nakeduml.javageneration.jbpm5.activity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.jbpm5.AbstractEventHandlerInserter;
import net.sf.nakeduml.javageneration.jbpm5.FromNode;
import net.sf.nakeduml.javageneration.jbpm5.Jbpm5Util;
import net.sf.nakeduml.javageneration.jbpm5.WaitForEventElements;
import net.sf.nakeduml.javageneration.jbpm5.actions.Jbpm5ActionBuilder;
import net.sf.nakeduml.javageneration.oclexpressions.ValueSpecificationUtil;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.actions.INakedAcceptEventAction;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.activities.INakedActivityEdge;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.commonbehaviors.GuardedFlow;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.stdlib.IOclLibrary;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

@StepDependency(phase = JavaTransformationPhase.class,requires = ActivityProcessImplementor.class,after = ActivityProcessImplementor.class)
public class ActivityMessageEventHandlerInserter extends AbstractEventHandlerInserter{
	private Jbpm5ActionBuilder<INakedActivityNode> actionBuilder;
	@VisitBefore(matchSubclasses = true)
	public void visitActivity(INakedActivity activity){
		if(activity.isProcess()){
			OJAnnotatedClass activityClass = findJavaClass(activity);
			super.implementEventHandling(activityClass, activity, getEventActions(activity));
		}
	}
	@Override
	protected void implementEventConsumption(OJOperation operationContext,FromNode fromNode,OJIfStatement ifTokenFound){
		OJBlock block = ifTokenFound.getThenPart();
		INakedAcceptEventAction node = (INakedAcceptEventAction) fromNode.getWaitingElement();
		storeArguments(ifTokenFound, node);
		block = checkWeight(operationContext, block, node);
		if(node.isImplicitFork()){
			block.addToStatements("consumed=true");
			block.addToStatements("waitingNode.flowToNode(\"" + Jbpm5Util.getArtificialForkName(node) + "\")");
		}else if(node.isImplicitDecision()){
			block.addToStatements("consumed=true");
			block.addToStatements("waitingNode.flowToNode(\"" + Jbpm5Util.getArtificialChoiceName(node) + "\")");
		}else{
			GuardedFlow flow = fromNode.getDefaultTransition();
			if(flow != null){
				// default flow/transition
				block.addToStatements("consumed=true");
				getActionBuilder().flowTo(block, ((INakedActivityEdge) flow).getEffectiveTarget());
			}
		}
	}
	private OJBlock checkWeight(OJOperation operationContext,OJBlock block,INakedActivityNode node){
		StringBuilder sb = new StringBuilder();
		// Check if all weights have been satisfied
		// NB!! the weight logic only makes sense on AcceptEventActions. This is the only place where outputpin value count equates to
		// weight. Everywhere else it is impossible to determine weight. In other places it could also lead to stuck contractedProcesses
		// TODO implement validation
		for(INakedActivityEdge edge:node.getDefaultOutgoing()){
			if(edge.getSource() instanceof INakedOutputPin){
				NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(edge.getActivity(), (INakedOutputPin) edge.getSource());
				if(edge.getWeight() != null){
					if(map.isCollection()){
						IClassifier integerType = getOclEngine().getOclLibrary().lookupStandardType(IOclLibrary.IntegerTypeName);
						if(edge.getWeight() != null){
						}
						String weight = ValueSpecificationUtil.expressValue(operationContext, edge.getWeight(), edge.getSource().getActivity(), integerType);
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
	private void storeArguments(OJIfStatement ifTokenFound,INakedAcceptEventAction aea){
		for(INakedOutputPin argument:aea.getResult()){
			NakedStructuralFeatureMap pinMap = OJUtil.buildStructuralFeatureMap(argument.getActivity(), argument);
			INakedTypedElement parm = argument.getLinkedTypedElement();
			if(parm == null){
				ifTokenFound.getThenPart().addToStatements(pinMap.setter() + "(unknown)");
			}else{
				if(pinMap.isOne()){
					ifTokenFound.getThenPart().addToStatements(pinMap.setter() + "(" + parm.getMappingInfo().getJavaName().toString() + ")");
				}else{
					if(parm.getNakedMultiplicity().isMany()){
						ifTokenFound.getThenPart().addToStatements(pinMap.allAdder() + "(" + parm.getMappingInfo().getJavaName().toString() + ")");
					}else{
						ifTokenFound.getThenPart().addToStatements(pinMap.adder() + "(" + parm.getMappingInfo().getJavaName().toString() + ")");
					}
				}
			}
		}
	}
	private Collection<WaitForEventElements> getEventActions(INakedActivity activity){
		Map<INakedElement,WaitForEventElements> results = new HashMap<INakedElement,WaitForEventElements>();
		for(INakedActivityNode node:activity.getActivityNodesRecursively()){
			if(node instanceof INakedAcceptEventAction){
				INakedAcceptEventAction action = (INakedAcceptEventAction) node;
				if(action.getTrigger() != null && action.getTrigger().getEvent() != null){
					WaitForEventElements eventActions = results.get(action.getTrigger().getEvent());
					if(eventActions == null){
						eventActions = new WaitForEventElements(action.getTrigger().getEvent());
						results.put(action.getTrigger().getEvent(), eventActions);
					}
					for(INakedActivityEdge flow:action.getAllEffectiveOutgoing()){
						eventActions.addWaitingNode(action, flow, true);
					}
				}
			}
		}
		return results.values();
	}
	private Jbpm5ActionBuilder<INakedActivityNode> getActionBuilder(){
		if(actionBuilder == null){
			actionBuilder = new Jbpm5ActionBuilder<INakedActivityNode>(workspace.getOclEngine(), null){
				@Override
				public void implementActionOn(OJAnnotatedOperation oper){
				}
			};
		}
		return actionBuilder;
	}
}
