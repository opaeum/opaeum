package org.nakeduml.bootstrap;

import java.util.List;

import net.sf.nakeduml.feature.InputModel;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.PhaseDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.TransformationPhase;
import net.sf.nakeduml.filegeneration.FileGenerationPhase;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.pomgeneration.PomGenerationPhase;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

@PhaseDependency(before = { FileGenerationPhase.class, PomGenerationPhase.class }, after = { JavaTransformationPhase.class })
public class BootstrapGenerationPhase implements TransformationPhase<AbstractBootstrapStep,INakedElement> {

	private NakedUmlConfig config;
	@InputModel
	private TextWorkspace textWorkspace;
	@InputModel
	private INakedModelWorkspace workspace;

	@Override
	public void initialize(NakedUmlConfig config) {
		this.config = config;
	}

	@Override
	public Object[] execute(List<AbstractBootstrapStep> features,TransformationContext context) {
		for (AbstractBootstrapStep step : features) {
			step.initialize(config, textWorkspace,context);
			step.startVisiting(workspace);
		}
		return new Object[0];
	}

	@Override
	public Object processSingleElement(List<AbstractBootstrapStep> features,TransformationContext context,INakedElement element){
		return element;
	}

}
