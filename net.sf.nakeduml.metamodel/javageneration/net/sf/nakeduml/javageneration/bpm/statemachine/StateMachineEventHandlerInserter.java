package net.sf.nakeduml.javageneration.bpm.statemachine;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.bpm.AbstractEventHandlerInserter;
import net.sf.nakeduml.javageneration.bpm.FromNode;
import net.sf.nakeduml.javageneration.bpm.WaitForEventElements;
import net.sf.nakeduml.javageneration.bpm.actions.AcceptEventActionBuilder;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJIfStatement;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.metamodel.commonbehaviors.GuardedFlow;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTimeEvent;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.statemachines.INakedState;
import net.sf.nakeduml.metamodel.statemachines.INakedStateMachine;
import net.sf.nakeduml.metamodel.statemachines.INakedTransition;
import net.sf.nakeduml.metamodel.statemachines.StateKind;

public class StateMachineEventHandlerInserter extends AbstractEventHandlerInserter{
	@VisitBefore(matchSubclasses = true)
	public void visitStateMachine(INakedStateMachine umlStateMachine){
		OJAnnotatedClass javaStateMachine = findJavaClass(umlStateMachine);
		Collection<WaitForEventElements> waitForEventElements = getWaitForEventElements(umlStateMachine);
		// TODO fire default transition after doActivity if it is a simple state
		for(WaitForEventElements wfe:waitForEventElements){
			if(wfe.getEvent() instanceof INakedTimeEvent){
				for(FromNode fromNode:wfe.getWaitingNodes()){
					String fireOper = "fireTimersFor" + fromNode.getWaitingElement().getMappingInfo().getJavaName();
					OJOperation fire = javaStateMachine.findOperation(fireOper, Collections.EMPTY_LIST);
					if(fire == null){
						fire = new OJAnnotatedOperation();
						fire.setName(fireOper);
						javaStateMachine.addToOperations(fire);
					}
					AcceptEventActionBuilder.implementTimeEvent(fire, (INakedTimeEvent) wfe.getEvent(), fromNode.getWaitingElement(), fromNode
							.getTransitions());
					String cancelOper = "cancelTimersFor" + fromNode.getWaitingElement().getMappingInfo().getJavaName();
					OJOperation cancel = javaStateMachine.findOperation(cancelOper, Collections.EMPTY_LIST);
					if(cancel == null){
						cancel = new OJAnnotatedOperation();
						cancel.setName(cancelOper);
						javaStateMachine.addToOperations(fire);
					}
					AcceptEventActionBuilder.cancelTimer(cancel, (INakedTimeEvent) wfe.getEvent());
				}
			}
		}
		super.implementEventHandling(javaStateMachine, umlStateMachine, getWaitForEventElements(umlStateMachine));
	};
	private Collection<WaitForEventElements> getWaitForEventElements(INakedStateMachine ns){
		Map<INakedElement,WaitForEventElements> results = new HashMap<INakedElement,WaitForEventElements>();
		for(INakedTransition transition:ns.getTransitions()){
			INakedElement trigger = transition.getTrigger();
			INakedState state = transition.getSource();
			if(trigger == null){
				if(transition.getSource().getKind() != StateKind.INITIAL){
					//Naughty!!!1
					trigger = state;
				}else{
					continue;
				}
			}
			WaitForEventElements eventActions = results.get(trigger);
			if(eventActions == null){
				eventActions = new WaitForEventElements(trigger);
				results.put(trigger, eventActions);
			}
			eventActions.addWaitingNode(state, transition, state.getKind().isRestingState());
		}
		return results.values();
	}
	@Override
	protected void implementEventConsumption(FromNode node,OJIfStatement ifNotNull){
		// TODO might want to cache the event parameters somewhere, or not
	}
	@Override
	protected void maybeContinueFlow(OJOperation operationContext,OJBlock block,GuardedFlow flow){
		super.maybeContinueFlow(operationContext, block, flow);
	}
}
