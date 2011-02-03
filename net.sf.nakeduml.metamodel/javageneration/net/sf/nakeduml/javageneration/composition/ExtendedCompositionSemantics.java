package net.sf.nakeduml.javageneration.composition;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.basicjava.BasicJavaModelStep;
import net.sf.nakeduml.javageneration.oclexpressions.OclExpressionExecution;
import net.sf.nakeduml.linkage.SourcePopulationResolver;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

@StepDependency(phase = JavaTransformationPhase.class,requires = {BasicJavaModelStep.class,OclExpressionExecution.class,SourcePopulationResolver.class},after = {BasicJavaModelStep.class,
		OclExpressionExecution.class})
public class ExtendedCompositionSemantics extends AbstractJavaTransformationStep{
	@Override
	public void generate(INakedModelWorkspace workspace,TransformationContext context){
		CompositionNodeImplementor cni = new CompositionNodeImplementor();
		cni.initialize(workspace, javaModel, config, textWorkspace);
		cni.startVisiting(workspace);
		ComponentInitializer ci = new ComponentInitializer();
		ci.initialize(workspace, javaModel, config, textWorkspace);
		ci.startVisiting(workspace);
		CopyMethodImplementor cmi = new CopyMethodImplementor();
		cmi.initialize(workspace, javaModel, config, textWorkspace);
		cmi.startVisiting(workspace);
		FactoryMethodCreator fmc = new FactoryMethodCreator();
		fmc.initialize(workspace, javaModel, config, textWorkspace);
		fmc.startVisiting(workspace);
		ConfigurableCompositionDataGenerator sctdg = new ConfigurableCompositionDataGenerator();
		sctdg.initialize(workspace, javaModel, config, textWorkspace);
		sctdg.startVisiting(workspace);
		
		ConfigurableCompositionTreeInitializer ccti = new ConfigurableCompositionTreeInitializer();
		ccti.initialize(workspace, javaModel, config, textWorkspace, sctdg.propertiesMap);
		ccti.startVisiting(workspace);

		ConfigurableCompositionPropertiesGenerator ccpg = new ConfigurableCompositionPropertiesGenerator();
		ccpg.initialize(workspace, javaModel, config, textWorkspace, ccti.propertiesMap);
		ccpg.startVisiting(workspace);
	}
}
