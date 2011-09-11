package org.nakeduml.bootstrap;

import java.util.Collection;
import java.util.List;

import net.sf.nakeduml.feature.InputModel;
import net.sf.nakeduml.feature.IntegrationPhase;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.PhaseDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.TransformationPhase;
import net.sf.nakeduml.filegeneration.FileGenerationPhase;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.pomgeneration.PomGenerationPhase;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

@PhaseDependency(before = {
		FileGenerationPhase.class,PomGenerationPhase.class
},after = {
	JavaTransformationPhase.class
})
public class BootstrapGenerationPhase implements TransformationPhase<AbstractBootstrapStep,INakedElement>,IntegrationPhase{
	private NakedUmlConfig config;
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
	public void initialize(NakedUmlConfig config,List<AbstractBootstrapStep> features){
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
