package net.sf.nakeduml.javageneration.hibernate.hbm;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.basicjava.BasicJavaModelStep;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.validation.namegeneration.PersistentNameGenerator;

@StepDependency(phase = HibernateHbmPhase.class,requires = {BasicJavaModelStep.class,PersistentNameGenerator.class},after = {BasicJavaModelStep.class})
public class HbmPersistence extends AbstractHbmTransformationStep {
	@Override
	public void generate(INakedModelWorkspace workspace,TransformationContext context){
		HbmClassGenerator hbmGenerator = new HbmClassGenerator();
//		hbmGenerator.initialize(workspace, javaModel, config, textWorkspace, this.hbmWorkspace);
		hbmGenerator.startVisiting(workspace);
		HbmJavaProducingVisitor hbmPropertyGenerator = new HbmPropertyGenerator();
//		hbmPropertyGenerator.initialize(workspace, javaModel, config, textWorkspace, this.hbmWorkspace);
		hbmPropertyGenerator.startVisiting(workspace);
	}
}
