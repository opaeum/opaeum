package org.nakeduml.tinker.auditing;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

import org.nakeduml.tinker.basicjava.jsf.TinkerJsfTransformation;

@StepDependency(phase = TinkerImplementCachePhase.class,requires=TinkerImplementAttributeCacheStep.class,after=TinkerImplementAttributeCacheStep.class)
public class TinkerJsfImplementationStep extends AbstractJavaTransformationStep {

	@Override
	public void generate(INakedModelWorkspace workspace, TransformationContext context) {
		TinkerJsfTransformation tinkerJsfTransformation = new TinkerJsfTransformation();
		tinkerJsfTransformation.initialize(javaModel, config, textWorkspace, context);
		tinkerJsfTransformation.startVisiting(workspace);
	}

}
