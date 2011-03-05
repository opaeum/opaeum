package net.sf.nakeduml.javageneration.jbpm5;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.filegeneration.TextFileGenerator;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.linkage.ProcessIdentifier;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.pomgeneration.WarPomStep;
import net.sf.nakeduml.pomgeneration.Seam3PomStep;

@StepDependency(phase = JavaTransformationPhase.class, requires = { ProcessIdentifier.class,TextFileGenerator.class}, after = {})
public class IntegratedJbpm5EnvironmentStep extends AbstractJavaTransformationStep {
	@Override
	public void generate(INakedModelWorkspace workspace, TransformationContext context) {
		Jbpm5EnvironmentBuilder jeb = new Jbpm5EnvironmentBuilder(true);
		jeb.initialize(javaModel, config, textWorkspace, context);
		jeb.startVisiting(workspace);
	}
}
