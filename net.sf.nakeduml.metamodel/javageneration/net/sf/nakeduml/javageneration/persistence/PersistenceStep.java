package net.sf.nakeduml.javageneration.persistence;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.basicjava.BasicJavaModelStep;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.validation.namegeneration.PersistentNameGenerator;

@StepDependency(phase = JavaTransformationPhase.class,requires = {BasicJavaModelStep.class,PersistentNameGenerator.class},after = {BasicJavaModelStep.class})
public class PersistenceStep extends AbstractJavaTransformationStep{

	@Override
	public void generate(INakedModelWorkspace workspace,TransformationContext context){
		AbstractEntityImplementor aei = new AbstractEntityImplementor();
		aei.initialize(workspace, javaModel, config, textWorkspace);
		aei.startVisiting(workspace);
		JpaAnnotator ea = new JpaAnnotator();
		ea.initialize(workspace, javaModel, config, textWorkspace);
		ea.startVisiting(workspace);
		
	}
}
