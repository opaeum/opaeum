package org.nakeduml.tinker.activity.nullify;

import java.util.Arrays;
import java.util.Collection;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.OperationAnnotator;
import org.opaeum.javageneration.basicjava.SimpleActivityMethodImplementor;
import org.opaeum.javageneration.jbpm5.activity.ActivityProcessImplementor;
import org.opaeum.javageneration.oclexpressions.CodeCleanup;
import org.opaeum.linkage.CompositionEmulator;
import org.opaeum.linkage.NakedParsedOclStringResolver;
import org.opaeum.linkage.PinLinker;
import org.opaeum.linkage.ProcessIdentifier;
import org.opaeum.metamodel.activities.ActivityNodeContainer;
import org.opaeum.metamodel.activities.INakedActivity;
import org.opaeum.metamodel.activities.INakedActivityEdge;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedElement;

@StepDependency(phase = JavaTransformationPhase.class, requires = { OperationAnnotator.class, PinLinker.class, ProcessIdentifier.class, CompositionEmulator.class,
		NakedParsedOclStringResolver.class, CodeCleanup.class }, after = { OperationAnnotator.class, SimpleActivityMethodImplementor.class /*
																																			 * Needs
																																			 * repeatable
																																			 * sequence
																																			 * in
																																			 * the
																																			 * ocl
																																			 * generating
																																			 * steps
																																			 */
}, before = CodeCleanup.class, replaces = ActivityProcessImplementor.class)
public class TinkerActivityProcessImplementor extends ActivityProcessImplementor {

	@VisitBefore(matchSubclasses = true)
	public void implementActivity(INakedActivity activity) {
	}

	public void implementVariableDelegation(ActivityNodeContainer container, INakedClassifier msg, OJAnnotatedClass c) {
	}

	protected void visitEdges(Collection<INakedActivityEdge> activityEdges) {
	}

	@Override
	protected Collection<? extends INakedElement> getTopLevelFlows(INakedClassifier umlBehavior) {
		return Arrays.asList(umlBehavior);
	}
}
