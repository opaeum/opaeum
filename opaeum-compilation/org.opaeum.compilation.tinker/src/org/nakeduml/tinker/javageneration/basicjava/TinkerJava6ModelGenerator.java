package org.nakeduml.tinker.javageneration.basicjava;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.Java6ModelGenerator;
import org.opaeum.javageneration.basicjava.UmlToJavaMapInitialiser;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedEnumeration;
import org.opaeum.metamodel.core.INakedInterface;
import org.opaeum.metamodel.core.INakedOperation;

@StepDependency(phase = JavaTransformationPhase.class, requires = { UmlToJavaMapInitialiser.class }, after = { UmlToJavaMapInitialiser.class }, replaces = Java6ModelGenerator.class)
public class TinkerJava6ModelGenerator extends Java6ModelGenerator {

	@VisitBefore(matchSubclasses = true)
	public void visitOperation(INakedOperation no) {
	}

	@VisitAfter(matchSubclasses = true, match = { INakedInterface.class, INakedEnumeration.class })
	public void visitClass(final INakedClassifier c) {
		super.visitClass(c);
	}

}
