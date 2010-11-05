package net.sf.nakeduml.javageneration.jbpm5.activity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.jbpm5.AbstractEventHandlerInserter;
import net.sf.nakeduml.javageneration.jbpm5.FromNode;
import net.sf.nakeduml.javageneration.jbpm5.WaitForEventElements;
import net.sf.nakeduml.javageneration.jbpm5.actions.Jbpm5ActionBuilder;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJIfStatement;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.metamodel.actions.INakedAcceptEventAction;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.activities.INakedActivityEdge;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.commonbehaviors.GuardedFlow;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

public class ActivityEventHandlerInserter extends AbstractEventHandlerInserter{
	private Jbpm5ActionBuilder<INakedActivityNode> actionBuilder;
	@Override
	public void initialize(INakedModelWorkspace workspace,OJPackage javaModel,NakedUmlConfig config,TextWorkspace textWorkspace){
		super.initialize(workspace, javaModel, config, textWorkspace);
		this.actionBuilder = new Jbpm5ActionBuilder(workspace.getOclEngine(), null){
			@Override
			public void implementActionOn(OJAnnotatedOperation oper){
			}
		};
	}
	@VisitBefore(matchSubclasses = true)
	public void visitClass(INakedClassifier c){
		if(c instanceof INakedActivity){
			INakedActivity activity = (INakedActivity) c;
			if(activity.isProcess()){
				OJAnnotatedClass activityClass = findJavaClass(activity);
				super.implementEventHandling(activityClass, activity, getEventActions(activity));
			}
		}
	}
	/**
	 * Overrides the default guard logic and extends it with weight evaluation logic
	 */
	@Override
	protected void maybeContinueFlow(OJOperation operationContext,OJBlock block,GuardedFlow flow){
		actionBuilder.maybeContinueFlow(operationContext, block, (INakedActivityEdge) flow);
	}
	@Override
	protected void implementEventConsumption(FromNode node,OJIfStatement ifNotNull){
		INakedAcceptEventAction aea = (INakedAcceptEventAction) node.getWaitingElement();
		for(INakedOutputPin argument:aea.getResult()){
			NakedStructuralFeatureMap pinMap = OJUtil.buildStructuralFeatureMap(argument.getActivity(), argument);
			INakedTypedElement parm = argument.getLinkedTypedElement();
			if(parm == null){
				ifNotNull.getThenPart().addToStatements(pinMap.setter() + "(unknown)");
			}else{
				if(pinMap.isOne()){
					ifNotNull.getThenPart().addToStatements(pinMap.setter() + "(" + parm.getMappingInfo().getJavaName().toString() + ")");
				}else{
					ifNotNull.getThenPart().addToStatements(pinMap.adder() + "(" + parm.getMappingInfo().getJavaName().toString() + ")");
				}
			}
		}
	}
	private Collection<WaitForEventElements> getEventActions(INakedActivity activity){
		Map<INakedElement,WaitForEventElements> results = new HashMap<INakedElement,WaitForEventElements>();
		for(INakedActivityNode node:activity.getActivityNodesRecursively()){
			if(node instanceof INakedAcceptEventAction){
				INakedAcceptEventAction action = (INakedAcceptEventAction) node;
				WaitForEventElements eventActions = results.get(action.getEvent());
				if(eventActions == null){
					eventActions = new WaitForEventElements(action.getEvent());
					results.put(action.getEvent(), eventActions);
				}
				for(INakedActivityEdge flow:action.getAllEffectiveOutgoing()){
					eventActions.addWaitingNode(action, flow,true);
				}
			}
		}
		return results.values();
	}
}
