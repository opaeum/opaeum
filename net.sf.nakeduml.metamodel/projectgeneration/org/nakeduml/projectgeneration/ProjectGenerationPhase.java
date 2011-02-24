package org.nakeduml.projectgeneration;

import java.util.List;

import net.sf.nakeduml.feature.InputModel;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.PhaseDependency;
import net.sf.nakeduml.feature.TransformationPhase;
import net.sf.nakeduml.filegeneration.FileGenerationPhase;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedPackage;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.pomgeneration.PomGenerationPhase;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

@PhaseDependency(before = { FileGenerationPhase.class, PomGenerationPhase.class }, after = { JavaTransformationPhase.class })
public class ProjectGenerationPhase implements TransformationPhase<AbstractProjectGenerationStep> {

	private NakedUmlConfig config;
	@InputModel
	private TextWorkspace textWorkspace;
	@InputModel
	private OJAnnotatedPackage javaModel;
	@InputModel
	private INakedModelWorkspace workspace;

	@Override
	public void initialize(NakedUmlConfig config) {
		this.config = config;
	}

	@Override
	public Object[] execute(List<AbstractProjectGenerationStep> features) {
		for (AbstractProjectGenerationStep step : features) {
			step.initialize(workspace, javaModel, config, textWorkspace);
			step.startVisiting(workspace);
		}
		return new Object[0];
	}

}
