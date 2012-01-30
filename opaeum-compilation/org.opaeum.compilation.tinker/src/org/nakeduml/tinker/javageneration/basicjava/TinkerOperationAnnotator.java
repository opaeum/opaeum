package org.nakeduml.tinker.javageneration.basicjava;

import org.opaeum.feature.StepDependency;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.AttributeImplementor;
import org.opaeum.javageneration.basicjava.OperationAnnotator;
import org.opaeum.javageneration.basicjava.SuperTypeGenerator;
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.core.INakedClassifier;

@StepDependency(phase = JavaTransformationPhase.class, requires = { AttributeImplementor.class, SuperTypeGenerator.class }, after = { AttributeImplementor.class,
		SuperTypeGenerator.class }, replaces = OperationAnnotator.class)
public class TinkerOperationAnnotator extends OperationAnnotator {

	@Override
	public void visitBehaviors(INakedBehavior o) {
	}

	@Override
	public void visitClass(INakedClassifier c) {
	}

}
