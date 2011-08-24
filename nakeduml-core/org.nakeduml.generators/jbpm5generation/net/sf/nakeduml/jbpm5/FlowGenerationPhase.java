package net.sf.nakeduml.jbpm5;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.nakeduml.feature.InputModel;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.PhaseDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.TransformationPhase;
import net.sf.nakeduml.feature.TransformationProcess.TransformationProgressLog;
import net.sf.nakeduml.filegeneration.FileGenerationPhase;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.TextFile;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

@PhaseDependency(after = JavaTransformationPhase.class,before = FileGenerationPhase.class)
public class FlowGenerationPhase implements TransformationPhase<AbstractFlowStep,INakedElement>{
	@InputModel
	INakedModelWorkspace workspace;
	@InputModel
	TextWorkspace textWorkspace;
	private NakedUmlConfig config;
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
	public void execute(TransformationProgressLog log,TransformationContext context){
		for(AbstractFlowStep step:flowSteps){
			if(!log.isCanceled()){
				step.startVisiting(workspace);
			}
		}
	}
	@Override
	public void initialize(NakedUmlConfig config,List<AbstractFlowStep> features){
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
