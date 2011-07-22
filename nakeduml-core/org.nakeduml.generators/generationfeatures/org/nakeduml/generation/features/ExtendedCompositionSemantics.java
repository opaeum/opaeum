package org.nakeduml.generation.features;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.basicjava.BasicJavaModelStep;
import net.sf.nakeduml.javageneration.composition.ExtendedCompositionSemanticsJavaStep;
import net.sf.nakeduml.javageneration.oclexpressions.OclExpressionExecution;
import net.sf.nakeduml.linkage.CompositionEmulator;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.pomgeneration.BasicJavaAdaptorPomStep;

@StepDependency(phase = JavaTransformationPhase.class,requires = {CompositionEmulator.class, BasicJavaAdaptorPomStep.class,ExtendedCompositionSemanticsJavaStep.class},after = {BasicJavaModelStep.class,
		OclExpressionExecution.class})
public class ExtendedCompositionSemantics extends AbstractJavaTransformationStep{
	@Override
	public void generate(INakedModelWorkspace workspace,TransformationContext context){
	}
}
