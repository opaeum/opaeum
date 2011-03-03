package net.sf.nakeduml.jbpm5;

import java.util.List;

import net.sf.nakeduml.feature.InputModel;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.PhaseDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.TransformationPhase;
import net.sf.nakeduml.filegeneration.FileGenerationPhase;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.TextWorkspace;
@PhaseDependency(after=JavaTransformationPhase.class, before=FileGenerationPhase.class)
public class FlowGenerationPhase implements TransformationPhase<FlowGenerationStep> {
	@InputModel
	INakedModelWorkspace workspace;
	@InputModel
	TextWorkspace textWorkspace;
	private NakedUmlConfig config;

	@Override
	public void initialize(NakedUmlConfig config) {
		this.config = config;
	}

	@Override
	public Object[] execute(List<FlowGenerationStep> features,TransformationContext context) {
		for (FlowGenerationStep step : features) {
			step.initialize(config,textWorkspace, workspace);
			step.startVisiting(workspace);
		}
		return new Object[]{textWorkspace};
	}
}
