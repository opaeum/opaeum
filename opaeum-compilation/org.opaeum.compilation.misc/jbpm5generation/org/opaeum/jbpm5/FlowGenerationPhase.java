package org.opaeum.jbpm5;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.opaeum.feature.InputModel;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.PhaseDependency;
import org.opaeum.feature.TransformationContext;
import org.opaeum.feature.TransformationPhase;
import org.opaeum.filegeneration.FileGenerationPhase;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.workspace.INakedModelWorkspace;
import org.opaeum.textmetamodel.TextOutputNode;
import org.opaeum.textmetamodel.TextWorkspace;

@PhaseDependency(after = JavaTransformationPhase.class,before = FileGenerationPhase.class)
public class FlowGenerationPhase implements TransformationPhase<AbstractFlowStep,INakedElement>{
	@InputModel
	INakedModelWorkspace workspace;
	@InputModel
	TextWorkspace textWorkspace;
	private OpaeumConfig config;
	private List<AbstractFlowStep> flowSteps;
	@Override
	public Collection<?> processElements(TransformationContext context,Collection<INakedElement> elements){
		Set<TextOutputNode> result = new HashSet<TextOutputNode>();
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
	public void initialize(OpaeumConfig config,List<AbstractFlowStep> features){
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
