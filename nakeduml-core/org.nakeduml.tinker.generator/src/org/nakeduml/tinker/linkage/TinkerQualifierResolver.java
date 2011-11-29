package org.nakeduml.tinker.linkage;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.linkage.LinkagePhase;
import net.sf.nakeduml.linkage.ReferenceResolver;
import net.sf.nakeduml.metamodel.core.INakedProperty;

@StepDependency(phase = LinkagePhase.class, replaces = ReferenceResolver.class)
public class TinkerQualifierResolver extends ReferenceResolver {

	@VisitBefore(matchSubclasses = true)
	public void linkQualifiers(INakedProperty property) {
	}

}
