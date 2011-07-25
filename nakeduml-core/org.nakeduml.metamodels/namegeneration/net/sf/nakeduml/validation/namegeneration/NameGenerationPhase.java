package net.sf.nakeduml.validation.namegeneration;

import java.util.List;

import net.sf.nakeduml.feature.InputModel;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.PhaseDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.TransformationPhase;
import net.sf.nakeduml.linkage.LinkagePhase;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

@PhaseDependency(after={LinkagePhase.class})
public class NameGenerationPhase implements TransformationPhase<AbstractNameGenerator,INakedElement>{
	@InputModel
	private INakedModelWorkspace modelWorkspace;
	public void initialize(NakedUmlConfig config){
	}
	public Object[] execute(List<AbstractNameGenerator> validators,TransformationContext context){
		for(AbstractNameGenerator ng:validators){
			ng.startVisiting(modelWorkspace);
		}
		return new Object[0];
	}
	@Override
	public Object processSingleElement(List<AbstractNameGenerator> features,TransformationContext context,INakedElement element){
		for(AbstractNameGenerator ng:features){
			ng.visitRecursively((INakedElementOwner) element);
		}
		return element;
	}
}
