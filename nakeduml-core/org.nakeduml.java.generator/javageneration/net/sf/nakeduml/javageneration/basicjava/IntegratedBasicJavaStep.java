package net.sf.nakeduml.javageneration.basicjava;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.filegeneration.TextFileGenerator;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.linkage.ProcessIdentifier;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

@StepDependency(phase = JavaTransformationPhase.class,requires = {},after = {})
public class IntegratedBasicJavaStep extends AbstractJavaTransformationStep{

	@Override
	public void generate(INakedModelWorkspace workspace,TransformationContext context){
		PersistentNameMapGenerator pnmg=new PersistentNameMapGenerator(true);
		pnmg.initialize(javaModel, config, textWorkspace, context);
		pnmg.startVisiting(workspace);
	}
}
