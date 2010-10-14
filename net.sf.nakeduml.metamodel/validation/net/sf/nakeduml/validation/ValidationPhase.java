package net.sf.nakeduml.validation;

import java.util.List;

import net.sf.nakeduml.feature.InputModel;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.PhaseDependency;
import net.sf.nakeduml.feature.TransformationPhase;
import net.sf.nakeduml.linkage.LinkagePhase;
import net.sf.nakeduml.metamodel.core.INakedPackage;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

@PhaseDependency(after = LinkagePhase.class)
public class ValidationPhase implements TransformationPhase<AbstractValidator> {
	private NakedUmlConfig config;
	@InputModel
	private INakedModelWorkspace modelWorkspace;

	public void initialize(NakedUmlConfig config) {
		this.config = config;
	}

	public Object[] execute(List<AbstractValidator> validators) {
		for (INakedPackage p : modelWorkspace.getGeneratingModelsOrProfiles()) {
			for (AbstractValidator v : validators) {
				v.initialize(modelWorkspace, config);
				v.startVisiting(p);
			}
		}
		return new Object[] {};
	}

}
