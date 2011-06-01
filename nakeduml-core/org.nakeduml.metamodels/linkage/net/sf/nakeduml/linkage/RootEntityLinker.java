package net.sf.nakeduml.linkage;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.bpm.INakedUserInRole;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.validation.CoreValidationRule;

@StepDependency(phase = LinkagePhase.class)
public class RootEntityLinker extends AbstractModelElementLinker {
	public static final String ID = RootEntityLinker.class.getName();
	int rootUserEntities = 0;

	@Override
	public void initialize(INakedModelWorkspace workspace, NakedUmlConfig config) {
		super.initialize(workspace, config);
		rootUserEntities = 0;
	}

	@VisitBefore(matchSubclasses = false)
	public void checkEntity(INakedUserInRole ew) {
		// TODO support interfaces
		//TODO read from BPM model
		if (!ew.hasSupertype() && isInUserModel(ew)) {
			rootUserEntities++;
			if (workspace.getRootUserEntity() != null) {
				getErrorMap().putError(ew, CoreValidationRule.ONE_ROOT_USER, "More than one rootUserEntity detected");
				if (rootUserEntities == 2) {
					getErrorMap()
							.putError(workspace.getRootUserEntity(), CoreValidationRule.ONE_ROOT_USER, "More than one rootUserEntity detected");
				}
			}
			this.workspace.setRootUserEntity(ew);
		}
	}

	private boolean isInUserModel(INakedElement e) {
		// TODO extend to support N models here.
		while (e.getOwnerElement() instanceof INakedElement) {
			e = (INakedElement) e.getOwnerElement();
			if (this.workspace.getGeneratingModelsOrProfiles().contains(e)) {
				return true;
			}
		}
		return false;
	}
}
