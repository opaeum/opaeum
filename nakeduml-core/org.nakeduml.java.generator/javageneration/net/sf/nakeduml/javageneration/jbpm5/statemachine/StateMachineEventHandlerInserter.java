package net.sf.nakeduml.javageneration.jbpm5.statemachine;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.NakedStateMap;
import net.sf.nakeduml.javageneration.basicjava.SimpleActivityMethodImplementor;
import net.sf.nakeduml.javageneration.jbpm5.AbstractEventHandlerInserter;
import net.sf.nakeduml.javageneration.jbpm5.EventUtil;
import net.sf.nakeduml.javageneration.jbpm5.FromNode;
import net.sf.nakeduml.javageneration.jbpm5.Jbpm5Util;
import net.sf.nakeduml.javageneration.jbpm5.WaitForEventElements;
import net.sf.nakeduml.javageneration.oclexpressions.ValueSpecificationUtil;
import net.sf.nakeduml.javageneration.util.ReflectionUtil;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.bpm.INakedDeadline;
import net.sf.nakeduml.metamodel.commonbehaviors.GuardedFlow;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedChangeEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedOpaqueBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTimeEvent;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.statemachines.INakedState;
import net.sf.nakeduml.metamodel.statemachines.INakedStateMachine;
import net.sf.nakeduml.metamodel.statemachines.INakedTransition;
import net.sf.nakeduml.metamodel.statemachines.StateKind;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.stdlib.IOclLibrary;

import org.nakeduml.java.metamodel.OJAnnonymousInnerClass;
import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJParameter;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.runtime.domain.TransitionListener;
import org.nakeduml.runtime.domain.UmlNodeInstance;

public class StateMachineEventHandlerInserter extends AbstractEventHandlerInserter{
	@VisitBefore(matchSubclasses = true)
	public void visitStateMachine(INakedStateMachine umlStateMachine){
		OJAnnotatedClass javaStateMachine = findJavaClass(umlStateMachine);
		Collection<WaitForEventElements> waitForEventElements = getWaitForEventElements(umlStateMachine);
		// TODO fire default transition after doActivity if it is a simple state
		for(WaitForEventElements wfe:waitForEventElements){
			if(wfe.getEvent() instanceof INakedDeadline){
				//fired and cancelled from task
			}else if(wfe.getEvent() instanceof INakedTimeEvent){
				for(FromNode fromNode:wfe.getWaitingNodes()){
					NakedStateMap map = new NakedStateMap((INakedState) fromNode.getWaitingElement());
					OJOperation fire = findOrCreateRequestEventsMethod(javaStateMachine, map);
					EventUtil.implementTimeEventRequest(fire, fire.getBody(), umlStateMachine, (INakedTimeEvent) wfe.getEvent(), "this");
					OJOperation cancel = findOrCreateCancelMethod(javaStateMachine, map);
					EventUtil.cancelTimer(cancel.getBody(), (INakedTimeEvent) wfe.getEvent(), "this");
				}
			}else if(wfe.getEvent() instanceof INakedEvent){
				for(FromNode fromNode:wfe.getWaitingNodes()){
					NakedStateMap map = new NakedStateMap((INakedState) fromNode.getWaitingElement());
					OJOperation fire = findOrCreateRequestEventsMethod(javaStateMachine, map);
					EventUtil.implementChangeEventRequest(fire, (INakedChangeEvent) wfe.getEvent());
					OJOperation cancel = findOrCreateCancelMethod(javaStateMachine, map);
					EventUtil.cancelChangeEvent(cancel.getBody(), (INakedChangeEvent) wfe.getEvent());
				}
			}
		}
		super.implementEventHandling(javaStateMachine, umlStateMachine, getWaitForEventElements(umlStateMachine));
	}
	protected OJOperation findOrCreateRequestEventsMethod(OJAnnotatedClass javaStateMachine,NakedStateMap map){
		String fireOper = map.getRequestEventsMethod();
		OJOperation fire = javaStateMachine.findOperation(fireOper, Collections.EMPTY_LIST);
		if(fire == null){
			fire = new OJAnnotatedOperation();
			fire.setName(fireOper);
			javaStateMachine.addToOperations(fire);
		}
		return fire;
	}
	protected OJOperation findOrCreateCancelMethod(OJAnnotatedClass javaStateMachine,NakedStateMap map){
		String cancelOper = map.getCancelEventsMethod();
		OJOperation cancel = javaStateMachine.findOperation(cancelOper, Collections.EMPTY_LIST);
		if(cancel == null){
			cancel = new OJAnnotatedOperation();
			cancel.setName(cancelOper);
			javaStateMachine.addToOperations(cancel);
		}
		return cancel;
	}
	private Collection<WaitForEventElements> getWaitForEventElements(INakedStateMachine ns){
		Map<INakedElement,WaitForEventElements> results = new HashMap<INakedElement,WaitForEventElements>();
		for(INakedTransition transition:ns.getTransitions()){
			INakedElement event = transition.getTrigger() == null ? null : transition.getTrigger().getEvent();
			INakedState state = transition.getSource();
			if(event == null){
				if(transition.getSource().getKind() != StateKind.INITIAL){
					// Naughty!!!
					event = state;
				}else{
					continue;
				}
			}
			WaitForEventElements eventActions = results.get(event);
			if(eventActions == null){
				eventActions = new WaitForEventElements(event);
				results.put(event, eventActions);
			}
			eventActions.addWaitingNode(state, transition, state.getKind().isRestingState());
		}
		return results.values();
	}
	@Override
	protected void implementEventConsumption(OJOperation operationContext,FromNode node,OJIfStatement ifTokenFound){
		OJIfStatement ifGuard = null;
		IClassifier booleanType = workspace.getOclEngine().getOclLibrary().lookupStandardType(IOclLibrary.BooleanTypeName);
		for(GuardedFlow t:node.getConditionalTransitions()){
			OJIfStatement newIf = new OJIfStatement();
			newIf.setCondition(ValueSpecificationUtil.expressValue(operationContext, t.getGuard(), t.getContext(), booleanType));
			newIf.getThenPart().addToStatements("consumed=true");
			maybeContinueFlow(operationContext, newIf.getThenPart(), t);
			OJBlock block1 = null;
			if(ifGuard == null){
				block1 = ifTokenFound.getThenPart();
			}else{
				block1 = new OJBlock();
				ifGuard.setElsePart(block1);
			}
			block1.addToStatements(newIf);
			ifGuard = newIf;
		}
		OJBlock block = null;
		if(ifGuard == null){
			block = ifTokenFound.getThenPart();
		}else{
			block = new OJBlock();
			ifGuard.setElsePart(block);
		}
		GuardedFlow flow = node.getDefaultTransition();
		if(flow != null){
			// default flow/transition
			block.addToStatements("consumed=true");
			maybeContinueFlow(operationContext, block, flow);
		}
	}
	private void maybeContinueFlow(OJOperation operationContext,OJBlock block,GuardedFlow flow){
		operationContext.getOwner().addToImports(ReflectionUtil.getUtilInterface(UmlNodeInstance.class));
		operationContext.getOwner().addToImports(ReflectionUtil.getUtilInterface(TransitionListener.class));
		OJAnnonymousInnerClass listener = new OJAnnonymousInnerClass(operationContext.getOwner().getPathName(), "listener",
				ReflectionUtil.getUtilInterface(TransitionListener.class));
		block.addToLocals(listener);
		OJAnnotatedOperation onTransition = new OJAnnotatedOperation("onTransition");
		listener.getClassDeclaration().addToOperations(onTransition);
		INakedTransition transition = (INakedTransition) flow;
		if(transition.getEffect() instanceof INakedActivity){
			for(OJParameter p:operationContext.getParameters()){
				p.setFinal(true);
			}
			SimpleActivityMethodImplementor ai = new SimpleActivityMethodImplementor();
			ai.initialize(javaModel, config, textWorkspace, this.transformationContext);
			ai.setWorkspace(workspace);
			ai.implementActivityOn((INakedActivity) transition.getEffect(), onTransition);
			operationContext.getOwner().addToImports(listener.getClassDeclaration().getImports());
		}else if(transition.getEffect() instanceof INakedOpaqueBehavior){
			INakedOpaqueBehavior b = (INakedOpaqueBehavior) transition.getEffect();
			if(b.getBody() != null){
				IClassifier voidType = getOclEngine().getOclLibrary().lookupStandardType(IOclLibrary.OclVoidTypeName);
				String expression = ValueSpecificationUtil.expressValue(onTransition, b.getBody(), transition.getStateMachine(), voidType);
				onTransition.getBody().addToStatements(expression);
			}
		}
		block.addToStatements("waitingNode.takeTransition(\"" + calculateTargetNodeName(transition) + "\", listener)");
	}
	private String calculateTargetNodeName(INakedTransition flow){
		if(flow.getTarget().getIncoming().size() > 1){
			return Jbpm5Util.getArtificialJoinName(flow.getTarget());
		}
		return flow.getTarget().getMappingInfo().getPersistentName().toString();
	}
}
