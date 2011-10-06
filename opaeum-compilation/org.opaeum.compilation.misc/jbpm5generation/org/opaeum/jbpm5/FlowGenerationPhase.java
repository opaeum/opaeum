package org.opeum.jbpm5;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.opeum.feature.InputModel;
import org.opeum.feature.OpeumConfig;
import org.opeum.feature.PhaseDependency;
import org.opeum.feature.TransformationContext;
import org.opeum.feature.TransformationPhase;
import org.opeum.filegeneration.FileGenerationPhase;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.metamodel.core.INakedElement;
import org.opeum.metamodel.workspace.INakedModelWorkspace;
import org.opeum.textmetamodel.TextFile;
import org.opeum.textmetamodel.TextWorkspace;

@PhaseDependency(after = JavaTransformationPhase.class,before = FileGenerationPhase.class)
public class FlowGenerationPhase implements TransformationPhase<AbstractFlowStep,INakedElement>{
	@InputModel
	INakedModelWorkspace workspace;
	@InputModel
	TextWorkspace textWorkspace;
	private OpeumConfig config;
	private List<AbstractFlowStep> flowSteps;
	@Override
	public Collection<?> processElements(TransformationContext context,Collection<INakedElement> elements){
		Set<TextFile> result = new HashSet<TextFile>();
		for(INakedElement element:elements){
			for(AbstractFlowStep step:flowSteps){
				step.initialize(config, textWorkspace, workspace);
				step.visitRecursively(element);
				result.addAll(step.getTextFiles());
			}
		}
		return result;
	}
	@Override
	public void execute(TransformationContext context){
		for(AbstractFlowStep step:flowSteps){
			if(!context.getLog().isCanceled()){
				step.startVisiting(workspace);
			}
		}
	}
	@Override
	public void initialize(OpeumConfig config,List<AbstractFlowStep> features){
		this.flowSteps = features;
		this.config = config;
		initializeSteps();
	}
	public void initializeSteps(){
		for(AbstractFlowStep step:this.flowSteps){
			step.initialize(this.config, textWorkspace, workspace);
		}
	}
	@Override
	public Collection<AbstractFlowStep> getSteps(){
		return flowSteps;
	}
}
