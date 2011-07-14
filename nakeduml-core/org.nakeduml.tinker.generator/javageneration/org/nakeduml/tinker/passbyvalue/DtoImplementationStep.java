package org.nakeduml.tinker.passbyvalue;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.basicjava.Java5ModelGenerationStep;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

@StepDependency(phase = JavaTransformationPhase.class, requires = { Java5ModelGenerationStep.class }, after = { Java5ModelGenerationStep.class })
public class DtoImplementationStep extends AbstractJavaTransformationStep {
	public static String DTO = "Dto";
	public static String ASSEMBLER = "Assembler";
	public static String CTRL = "Controller";
	public static String WS = "Ws";
	@Override
	public void generate(INakedModelWorkspace workspace, TransformationContext context) {
		DtoImplementor dtoImplementor = new DtoImplementor();
		dtoImplementor.initialize(javaModel, config, textWorkspace, context);
		dtoImplementor.startVisiting(workspace);
		DtoAssemblerImplementor dtoAssemblerImplementor = new DtoAssemblerImplementor();
		dtoAssemblerImplementor.initialize(javaModel, config, textWorkspace, context);
		dtoAssemblerImplementor.startVisiting(workspace);
	}
}
