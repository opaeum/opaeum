package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.jbpm5.EventUtil;
import net.sf.nakeduml.javageneration.jbpm5.activity.ActivityEventConsumptionImplementor;
import net.sf.nakeduml.javageneration.jbpm5.statemachine.StateMachineEventConsumptionImplementor;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavioredClassifier;
import net.sf.nakeduml.metamodel.statemachines.INakedStateMachine;

import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		StateMachineEventConsumptionImplementor.class,ActivityEventConsumptionImplementor.class
},after = {
		StateMachineEventConsumptionImplementor.class,ActivityEventConsumptionImplementor.class
})
public class EventGeneratorImplementor extends AbstractJavaProducingVisitor{
	@VisitBefore(matchSubclasses = true)
	public void visitBehavioredClassifier(INakedBehavioredClassifier s){
		if(OJUtil.hasOJClass(s)){
			OJAnnotatedClass ojClass = findJavaClass(s);
			EventUtil.addOutgoingEventManagement(ojClass);
		}
	}
	private boolean hasOutgoingEvents(INakedStateMachine sm){
		return false;
	}
	private boolean hasOutgoingEvents(INakedActivity a){
		return hasOutgoingEvents((INakedBehavioredClassifier) a);
	}
	private boolean hasOutgoingEvents(INakedBehavioredClassifier bc){
		return false;
	}
}
