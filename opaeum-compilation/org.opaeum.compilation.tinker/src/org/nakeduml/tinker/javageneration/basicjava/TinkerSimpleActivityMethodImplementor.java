package org.nakeduml.tinker.javageneration.basicjava;

import org.opaeum.feature.StepDependency;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.OperationAnnotator;
import org.opaeum.javageneration.basicjava.SimpleActivityMethodImplementor;
import org.opaeum.javageneration.oclexpressions.PreAndPostConditionGenerator;
import org.opaeum.metamodel.activities.INakedActivity;
import org.opaeum.metamodel.commonbehaviors.INakedBehavioredClassifier;

@StepDependency(phase = JavaTransformationPhase.class, requires = { OperationAnnotator.class }, after = { OperationAnnotator.class, PreAndPostConditionGenerator.class }, replaces=SimpleActivityMethodImplementor.class)
public class TinkerSimpleActivityMethodImplementor extends SimpleActivityMethodImplementor {

	@Override
	public void implementActivities(INakedBehavioredClassifier bc) {
	}

	@Override
	public void implementActivityOn(INakedActivity a, OJAnnotatedOperation oper) {
	}
	
}
