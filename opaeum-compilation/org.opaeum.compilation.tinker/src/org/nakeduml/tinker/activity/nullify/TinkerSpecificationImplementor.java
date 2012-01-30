package org.nakeduml.tinker.activity.nullify;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.OperationAnnotator;
import org.opaeum.javageneration.basicjava.SpecificationImplementor;
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.commonbehaviors.INakedBehavioredClassifier;

@StepDependency(phase = JavaTransformationPhase.class, requires = { OperationAnnotator.class }, after = { OperationAnnotator.class }, replaces = SpecificationImplementor.class)
public class TinkerSpecificationImplementor extends SpecificationImplementor {

	@Override
	@VisitBefore(matchSubclasses = true)
	public void visitBehavior(INakedBehavior ob) {
	}

	@Override
	@VisitBefore(matchSubclasses = true)
	public void visitClassifier(INakedBehavioredClassifier c) {
	}

}
