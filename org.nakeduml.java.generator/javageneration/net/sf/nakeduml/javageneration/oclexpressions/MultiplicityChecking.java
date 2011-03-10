package net.sf.nakeduml.javageneration.oclexpressions;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.basicjava.BasicJavaModelStep;
import net.sf.nakeduml.metamodel.core.INakedPackage;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import nl.klasse.octopus.codegen.umlToJava.othergenerators.visitors.MultCheckGenerator;

@StepDependency(phase = JavaTransformationPhase.class, requires = { BasicJavaModelStep.class }, after = { BasicJavaModelStep.class })
public class MultiplicityChecking extends AbstractJavaTransformationStep {
	private MultiplicityChecking() {
		super();
	}

	@Override
	public void generate(INakedModelWorkspace workspace, TransformationContext context) {
		MultCheckGenerator maker = new MultCheckGenerator(javaModel);
		for (INakedPackage p : workspace.getGeneratingModelsOrProfiles()) {
			p.accept(maker);
		}
	}
}