package net.sf.nakeduml.javageneration.basicjava;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.validation.namegeneration.JavaNameRegenerator;
import net.sf.nakeduml.validation.namegeneration.PersistentNameGenerator;

@StepDependency(phase = JavaTransformationPhase.class,requires = {JavaNameRegenerator.class,PersistentNameGenerator.class},after = {})
public class IntegratedBasicJavaStep extends AbstractJavaTransformationStep{

	@Override
	public void generate(INakedModelWorkspace workspace,TransformationContext context){
		PersistentNameMapGenerator pnmg=new PersistentNameMapGenerator(true);
		pnmg.initialize(javaModel, config, textWorkspace, context);
		pnmg.startVisiting(workspace);
	}
}
