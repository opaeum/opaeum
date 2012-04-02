package org.nakeduml.tinker.linkage;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.linkage.DependencyCalculator;
import org.opaeum.linkage.LinkagePhase;
import org.opaeum.linkage.ProcessIdentifier;
import org.opaeum.metamodel.activities.ActivityKind;
import org.opaeum.metamodel.activities.INakedActivity;

@StepDependency(phase = LinkagePhase.class, after = { DependencyCalculator.class }, requires = { DependencyCalculator.class }, replaces = ProcessIdentifier.class)
public class TinkerProcessIdentifier extends ProcessIdentifier {

	@VisitBefore
	public void visitBehavior(INakedActivity a) {
		a.setActivityKind(ActivityKind.PROCESS);
	}

}
