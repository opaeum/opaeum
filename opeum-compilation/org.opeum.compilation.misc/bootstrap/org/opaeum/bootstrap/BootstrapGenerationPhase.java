package org.opeum.bootstrap;

import java.util.Collection;
import java.util.List;

import org.opeum.feature.InputModel;
import org.opeum.feature.IntegrationPhase;
import org.opeum.feature.OpeumConfig;
import org.opeum.feature.PhaseDependency;
import org.opeum.feature.TransformationContext;
import org.opeum.feature.TransformationPhase;
import org.opeum.filegeneration.FileGenerationPhase;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.metamodel.core.INakedElement;
import org.opeum.metamodel.workspace.INakedModelWorkspace;
import org.opeum.pomgeneration.PomGenerationPhase;
import org.opeum.textmetamodel.TextWorkspace;

@PhaseDependency(before = {
		FileGenerationPhase.class,PomGenerationPhase.class
},after = {
	JavaTransformationPhase.class
})
public class BootstrapGenerationPhase implements TransformationPhase<AbstractBootstrapStep,INakedElement>,IntegrationPhase{
	private OpeumConfig config;
	@InputModel
	private TextWorkspace textWorkspace;
	@InputModel
	private INakedModelWorkspace workspace;
	private List<AbstractBootstrapStep> features;
	@Override
	public Collection<?> processElements(TransformationContext context,Collection<INakedElement> elements){
		return elements;
	}
	@Override
	public void execute(TransformationContext context){
		for(AbstractBootstrapStep step:features){
			if(!context.getLog().isCanceled()){
				step.setTransformationContext(context);
				step.startVisiting(workspace);
			}
		}
	}
	@Override
	public void initialize(OpeumConfig config,List<AbstractBootstrapStep> features){
		this.config = config;
		this.features = features;
	}
	public void initializeSteps(){
		for(AbstractBootstrapStep step:this.features){
			step.initialize(this.config, textWorkspace, workspace);
		}
	}
	@Override
	public Collection<AbstractBootstrapStep> getSteps(){
		return features;
	}
}
