package org.nakeduml.tinker.auditing;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

import org.nakeduml.tinker.basicjava.tinker.TinkerAttributeCacheImplementor;
import org.nakeduml.tinker.basicjava.tinker.TinkerFieldAdderImplementor;

@StepDependency(phase = TinkerImplementCachePhase.class)
public class TinkerImplementAttributeCacheStep extends AbstractJavaTransformationStep  {

	@Override
	public void generate(INakedModelWorkspace workspace, TransformationContext context) {
		TinkerFieldAdderImplementor fieldAdderImplementor = new TinkerFieldAdderImplementor();
		fieldAdderImplementor.initialize(javaModel, config, textWorkspace, context);
		fieldAdderImplementor.startVisiting(workspace);
		TinkerAttributeCacheImplementor tinkerAttributeCacheImplementor = new TinkerAttributeCacheImplementor();
		tinkerAttributeCacheImplementor.initialize(javaModel, config, textWorkspace, context);
		tinkerAttributeCacheImplementor.startVisiting(workspace);
	}
}
