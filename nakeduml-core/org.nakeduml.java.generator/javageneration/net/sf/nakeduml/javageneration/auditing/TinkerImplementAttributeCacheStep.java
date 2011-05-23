package net.sf.nakeduml.javageneration.auditing;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.basicjava.tinker.TinkerAttributeCacheImplementor;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

@StepDependency(phase = TinkerImplementCachePhase.class)
public class TinkerImplementAttributeCacheStep extends AbstractJavaTransformationStep  {

	@Override
	public void generate(INakedModelWorkspace workspace, TransformationContext context) {
		TinkerAttributeCacheImplementor tinkerAttributeCacheImplementor = new TinkerAttributeCacheImplementor();
		tinkerAttributeCacheImplementor.initialize(javaModel, config, textWorkspace, context);
		tinkerAttributeCacheImplementor.startVisiting(workspace);
	}
}
