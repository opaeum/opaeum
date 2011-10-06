package org.opeum.validation.namegeneration;

import java.util.Collection;
import java.util.List;

import org.opeum.feature.InputModel;
import org.opeum.feature.OpeumConfig;
import org.opeum.feature.PhaseDependency;
import org.opeum.feature.TransformationContext;
import org.opeum.feature.TransformationPhase;
import org.opeum.linkage.LinkagePhase;
import org.opeum.metamodel.core.INakedElement;
import org.opeum.metamodel.core.INakedElementOwner;
import org.opeum.metamodel.core.INakedRootObject;
import org.opeum.metamodel.core.RootObjectStatus;
import org.opeum.metamodel.workspace.INakedModelWorkspace;

@PhaseDependency(after = {
	LinkagePhase.class
})
public class NameGenerationPhase implements TransformationPhase<AbstractNameGenerator,INakedElement>{
	@InputModel
	private INakedModelWorkspace modelWorkspace;
	private List<AbstractNameGenerator> nameGenerators;
	private OpeumConfig config;
	public void initialize(OpeumConfig config){
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
	public void execute(TransformationContext context){
		context.getLog().startTask("Generating platform-specific names", nameGenerators.size());
		for(AbstractNameGenerator ng:nameGenerators){
			context.getLog().startStep("Executing " + ng.getClass().getSimpleName());
			ng.startVisiting(modelWorkspace);
			context.getLog().endLastStep();
		}
		for(INakedRootObject ro:modelWorkspace.getRootObjects()){
			ro.setStatus(RootObjectStatus.NAMED);
		}
		context.getLog().endLastTask();
	}
	@Override
	public void initialize(OpeumConfig config,List<AbstractNameGenerator> features){
		this.nameGenerators = features;
		this.config = config;
	}
	@Override
	public Collection<AbstractNameGenerator> getSteps(){
		return this.nameGenerators;
	}
	@Override
	public void initializeSteps(){
		for(AbstractNameGenerator ng:nameGenerators){
			ng.initialize(config);
		}
	}
}
