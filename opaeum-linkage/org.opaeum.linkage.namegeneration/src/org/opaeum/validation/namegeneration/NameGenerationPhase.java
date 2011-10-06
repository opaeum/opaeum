package org.opaeum.validation.namegeneration;

import java.util.Collection;
import java.util.List;

import org.opaeum.feature.InputModel;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.PhaseDependency;
import org.opaeum.feature.TransformationContext;
import org.opaeum.feature.TransformationPhase;
import org.opaeum.linkage.LinkagePhase;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedElementOwner;
import org.opaeum.metamodel.core.INakedRootObject;
import org.opaeum.metamodel.core.RootObjectStatus;
import org.opaeum.metamodel.workspace.INakedModelWorkspace;

@PhaseDependency(after = {
	LinkagePhase.class
})
public class NameGenerationPhase implements TransformationPhase<AbstractNameGenerator,INakedElement>{
	@InputModel
	private INakedModelWorkspace modelWorkspace;
	private List<AbstractNameGenerator> nameGenerators;
	private OpaeumConfig config;
	public void initialize(OpaeumConfig config){
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
	public void initialize(OpaeumConfig config,List<AbstractNameGenerator> features){
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
