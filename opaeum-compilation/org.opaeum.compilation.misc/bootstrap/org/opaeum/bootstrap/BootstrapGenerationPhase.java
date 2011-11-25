package org.opaeum.bootstrap;

import java.util.Collection;
import java.util.List;

import org.opaeum.feature.InputModel;
import org.opaeum.feature.IntegrationPhase;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.PhaseDependency;
import org.opaeum.feature.TransformationContext;
import org.opaeum.feature.TransformationPhase;
import org.opaeum.filegeneration.FileGenerationPhase;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.workspace.INakedModelWorkspace;
import org.opaeum.pomgeneration.PomGenerationPhase;
import org.opaeum.textmetamodel.TextWorkspace;

@PhaseDependency(before = {
		FileGenerationPhase.class,PomGenerationPhase.class
},after = {
	JavaTransformationPhase.class
})
public class BootstrapGenerationPhase implements TransformationPhase<AbstractBootstrapStep,INakedElement>,IntegrationPhase{
	private OpaeumConfig config;
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
	public void initialize(OpaeumConfig config,List<AbstractBootstrapStep> features){
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
	@Override
	public void release(){
		this.textWorkspace = null;
		this.workspace = null;
		for(AbstractBootstrapStep b:this.features){
			b.release();
		}
	}
}
