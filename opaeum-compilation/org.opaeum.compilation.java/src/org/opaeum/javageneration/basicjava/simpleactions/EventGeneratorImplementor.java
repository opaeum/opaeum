package org.opaeum.javageneration.basicjava.simpleactions;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.jbpm5.EventUtil;
import org.opaeum.javageneration.jbpm5.activity.ActivityEventConsumptionImplementor;
import org.opaeum.javageneration.jbpm5.statemachine.StateMachineEventConsumptionImplementor;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.commonbehaviors.INakedBehavioredClassifier;

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
}
