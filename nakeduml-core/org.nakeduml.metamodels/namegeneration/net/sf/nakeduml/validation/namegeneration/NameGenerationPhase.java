package net.sf.nakeduml.validation.namegeneration;

import java.util.Collection;
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
	private List<AbstractNameGenerator> nameGenerators;
	public void initialize(NakedUmlConfig config){
	}
	public Object[] execute(List<AbstractNameGenerator> nameGenerators,TransformationContext context){
		this.nameGenerators=nameGenerators;
		for(AbstractNameGenerator ng:nameGenerators){
			ng.startVisiting(modelWorkspace);
		}
		return new Object[0];
	}
	@Override
	public Collection<?> processElements(TransformationContext context,Collection<INakedElement> elements){
		for(INakedElement element:elements){
			for(AbstractNameGenerator ng:nameGenerators){
				ng.visitRecursively((INakedElementOwner) element);
			}
		}
		return elements;
	}
}
