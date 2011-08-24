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
	@Override
	public Collection<?> processElements(TransformationContext context,Collection<INakedElement> elements){
		for(INakedElement element:LinkagePhase.filterChildrenOut(elements)){
			for(AbstractNameGenerator ng:nameGenerators){
				ng.visitRecursively((INakedElementOwner) element);
			}
		}
		return elements;
	}
	@Override
	public void execute(net.sf.nakeduml.feature.TransformationProcess.TransformationProgressLog log, TransformationContext context){
		for(AbstractNameGenerator ng:nameGenerators){
			ng.startVisiting(modelWorkspace);
		}
	}
	@Override
	public void initialize(NakedUmlConfig config,List<AbstractNameGenerator> features){
		this.nameGenerators=features;
		
	}
	@Override
	public Collection<AbstractNameGenerator> getSteps(){
		return this.nameGenerators;
	}
	@Override
	public void initializeSteps(){
		for(AbstractNameGenerator ng:nameGenerators){
			ng.initialize();
		}
	}

}
