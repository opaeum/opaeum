package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.runtime.domain.IActiveObject;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.jbpm5.AbstractEventHandlerInserter;
import net.sf.nakeduml.javageneration.jbpm5.EventUtil;
import net.sf.nakeduml.javageneration.jbpm5.activity.ActivityEventHandlerInserter;
import net.sf.nakeduml.javageneration.jbpm5.statemachine.StateMachineEventHandlerInserter;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.INakedAcceptEventAction;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.actions.INakedInvocationAction;
import net.sf.nakeduml.metamodel.actions.INakedSendSignalAction;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.bpm.INakedAcceptDeadlineAction;
import net.sf.nakeduml.metamodel.bpm.INakedBusinessRole;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedTask;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavioredClassifier;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedEvent;
import net.sf.nakeduml.metamodel.statemachines.INakedStateMachine;
import net.sf.nakeduml.metamodel.statemachines.INakedTransition;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		StateMachineEventHandlerInserter.class,ActivityEventHandlerInserter.class
},after = {
		StateMachineEventHandlerInserter.class,ActivityEventHandlerInserter.class
})
public class EventManagementImplementor extends AbstractJavaProducingVisitor{
	@VisitBefore(matchSubclasses = true)
	public void visitBehavioredClassifier(INakedBehavioredClassifier s){
		OJAnnotatedClass ojClass = findJavaClass(s);
		if(hasOutgoingEvents(s)){
			EventUtil.addOutgoingEventManagement(ojClass);
		}else if(s instanceof INakedStateMachine && hasOutgoingEvents((INakedStateMachine) s)){
			EventUtil.addOutgoingEventManagement(ojClass);
		}else if(s instanceof INakedActivity && BehaviorUtil.hasExecutionInstance((INakedActivity)s) && hasOutgoingEvents((INakedActivity) s)){
			EventUtil.addOutgoingEventManagement(ojClass);
		}

		if(s instanceof INakedBusinessRole || s.getOwnedReception().size() > 0){
			OJAnnotatedOperation oper = AbstractEventHandlerInserter.ensureProcessSignalPresent(ojClass);
			if(oper.getBody().getStatements().size()==0){
				oper.getBody().addToStatements("return false");
			}
		}
	}
	private boolean hasOutgoingEvents(INakedStateMachine sm){
		for(INakedTransition t:sm.getTransitions()){
			if(t.getTrigger()!=null && t.getTrigger().getEvent() instanceof INakedEvent){
				return true;
			}else if(t.getEffect() instanceof INakedActivity){
				if(hasOutgoingEvents((INakedActivity) t.getEffect())){
					return true;
				}
			}
		}
		return false;
	}
	private boolean hasOutgoingEvents(INakedActivity a){
		for(INakedActivityNode n:a.getActivityNodesRecursively()){
			if(n instanceof INakedAcceptDeadlineAction || n instanceof INakedAcceptEventAction || n instanceof INakedSendSignalAction){
				return true;
			}else if(n instanceof INakedEmbeddedTask && ((INakedEmbeddedTask) n).getTaskDefinition().getDeadlines().size() > 0){
				return true;
			}else if(n instanceof INakedCallAction && !((INakedCallAction) n).isSynchronous()){
				return true;
			}
		}
		return hasOutgoingEvents((INakedBehavioredClassifier)a);
	}
	private boolean hasOutgoingEvents(INakedBehavioredClassifier bc){
		for(INakedBehavior b:bc.getOwnedBehaviors()){
			if(b instanceof INakedActivity && !b.isProcess() && hasOutgoingEvents((INakedActivity) b)){
				return true;
			}
		}
		return false;
	}
}
