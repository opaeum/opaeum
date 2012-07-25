package org.nakeduml.tinker.javageneration.basicjava;

import org.eclipse.uml2.uml.INakedBehavior;
import org.eclipse.uml2.uml.INakedClassifier;
import org.opaeum.feature.StepDependency;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.AttributeImplementor;
import org.opaeum.javageneration.basicjava.OperationAnnotator;
import org.opaeum.javageneration.basicjava.SuperTypeGenerator;

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
