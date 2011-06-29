package net.sf.nakeduml.javageneration.composition;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.basicjava.BasicJavaModelStep;
import net.sf.nakeduml.javageneration.oclexpressions.OclExpressionExecution;
import net.sf.nakeduml.linkage.SourcePopulationResolver;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
@StepDependency(phase = JavaTransformationPhase.class,requires = {BasicJavaModelStep.class,OclExpressionExecution.class,SourcePopulationResolver.class},after = {
	BasicJavaModelStep.class,OclExpressionExecution.class})

public class DataGeneration extends AbstractJavaTransformationStep{
	@Override
	public void generate(INakedModelWorkspace workspace,TransformationContext context){
		ConfigurableCompositionDataGenerator sctdg = new ConfigurableCompositionDataGenerator();
		sctdg.initialize(javaModel, config, textWorkspace, context);
		sctdg.startVisiting(workspace);
		ConfigurableCompositionPropertiesGenerator ccpg = new ConfigurableCompositionPropertiesGenerator();
		ccpg.initialize(javaModel, config, textWorkspace, context);
		ccpg.setModelInstanceMap(sctdg.modelInstanceMap);
		ccpg.startVisiting(workspace);
	}
}
