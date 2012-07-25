package org.nakeduml.tinker.linkage;

import org.eclipse.uml2.uml.INakedProperty;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.linkage.LinkagePhase;
import org.opaeum.linkage.ReferenceResolver;

@StepDependency(phase = LinkagePhase.class, replaces = ReferenceResolver.class)
public class TinkerQualifierResolver extends ReferenceResolver {

	@VisitBefore(matchSubclasses = true)
	public void linkQualifiers(INakedProperty property) {
	}

}
