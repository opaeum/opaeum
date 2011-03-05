package net.sf.nakeduml.javageneration.composition;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.basicjava.BasicJavaModelStep;
import net.sf.nakeduml.javageneration.oclexpressions.OclExpressionExecution;
import net.sf.nakeduml.linkage.SourcePopulationResolver;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.pomgeneration.BasicJavaAdaptorPomStep;
import net.sf.nakeduml.pomgeneration.Seam3PomStep;

import org.apache.commons.lang.time.StopWatch;

@StepDependency(phase = JavaTransformationPhase.class, requires = { BasicJavaModelStep.class, OclExpressionExecution.class,
		SourcePopulationResolver.class, BasicJavaAdaptorPomStep.class }, after = { BasicJavaModelStep.class, OclExpressionExecution.class })
public class ExtendedCompositionSemantics extends AbstractJavaTransformationStep {
	@Override
	public void generate(INakedModelWorkspace workspace, TransformationContext context) {
		CompositionNodeImplementor cni = new CompositionNodeImplementor();
		cni.initialize(javaModel, config, textWorkspace, context);
		cni.startVisiting(workspace);
		ComponentInitializer ci = new ComponentInitializer();
		ci.initialize(javaModel, config, textWorkspace, context);
		ci.startVisiting(workspace);
		CopyMethodImplementor cmi = new CopyMethodImplementor();
		cmi.initialize(javaModel, config, textWorkspace, context);
		cmi.startVisiting(workspace);
		FactoryMethodCreator fmc = new FactoryMethodCreator();
		fmc.initialize(javaModel, config, textWorkspace, context);
		fmc.startVisiting(workspace);
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		ConfigurableCompositionDataGenerator sctdg = new ConfigurableCompositionDataGenerator();
		sctdg.initialize(javaModel, config, textWorkspace, context);
		sctdg.startVisiting(workspace);
		ConfigurableCompositionPropertiesGenerator ccpg = new ConfigurableCompositionPropertiesGenerator();
		ccpg.initialize(javaModel, config, textWorkspace, context);
		ccpg.setModelInstanceMap(sctdg.modelInstanceMap);
		// ccpg.startVisiting(workspace);
		stopWatch.split();
		System.out.println("data generation took " + stopWatch.toSplitString());
	}
}
