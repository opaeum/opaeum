package net.sf.nakeduml.linkage;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.bpm.INakedBusinessRole;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.validation.CoreValidationRule;

@StepDependency(phase = LinkagePhase.class)
public class RootEntityLinker extends AbstractModelElementLinker {
	int rootUserEntities = 0;

	@Override
	public void initialize(INakedModelWorkspace workspace, NakedUmlConfig config) {
		super.initialize(workspace, config);
		rootUserEntities = 0;
	}

	@VisitBefore(matchSubclasses = false)
	public void checkEntity(INakedBusinessRole ew) {

	}

}
