package org.nakeduml.tinker.composition.tinker;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.composition.ExtendedCompositionSemanticsJavaStep;
import net.sf.nakeduml.linkage.InverseCalculator;
import net.sf.nakeduml.linkage.SourcePopulationResolver;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.validation.namegeneration.PersistentNameGenerator;

import org.nakeduml.tinker.basicjava.TinkerFieldRemoverStep;
import org.nakeduml.tinker.basicjava.tinker.TinkerSchemaGenerator;
import org.nakeduml.tinker.basicjava.tinker.TinkerTransformation;

@StepDependency(phase = JavaTransformationPhase.class,requires = {InverseCalculator.class, PersistentNameGenerator.class, TinkerFieldRemoverStep.class, SourcePopulationResolver.class},after = {
	InverseCalculator.class, ExtendedCompositionSemanticsJavaStep.class})
public class TinkerExtendedCompositionSemanticsJavaStep extends AbstractJavaTransformationStep{
	@Override
	public void generate(INakedModelWorkspace workspace,TransformationContext context){
		TinkerTransformation tinkerTransformation = new TinkerTransformation();
		tinkerTransformation.initialize(javaModel, config, textWorkspace, context);
		tinkerTransformation.startVisiting(workspace);
		TinkerSchemaGenerator schemaGenerator = new TinkerSchemaGenerator(); 
		schemaGenerator.initialize(javaModel, config, textWorkspace, context);
		schemaGenerator.startVisiting(workspace);
	}
}
