package net.sf.nakeduml.filegeneration;

import java.util.Collection;
import java.util.List;

import net.sf.nakeduml.feature.InputModel;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.PhaseDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.TransformationPhase;
import net.sf.nakeduml.feature.TransformationProcess.TransformationProgressLog;
import net.sf.nakeduml.textmetamodel.TextOutputNode;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

@PhaseDependency()
public class FileGenerationPhase implements TransformationPhase<AbstractTextNodeVisitor,TextOutputNode>{
	@InputModel
	private TextWorkspace textWorkspace;
	private NakedUmlConfig config;
	private List<AbstractTextNodeVisitor> features;
	public void initialize(NakedUmlConfig config){
		this.config = config;
	}
	@Override
	public Collection<?> processElements(TransformationContext context,Collection<TextOutputNode> elements){
		for(TextOutputNode element:elements){
			for(AbstractTextNodeVisitor feature:features){
				feature.initialize(config);
				feature.visitRecursively(element);
			}
		}
		return elements;
	}
	@Override
	public void execute(TransformationContext context){
		for(AbstractTextNodeVisitor feature:features){
			if(!context.getLog().isCanceled()){
				feature.startVisiting(textWorkspace);
			}
		}
	}
	@Override
	public void initialize(NakedUmlConfig config,List<AbstractTextNodeVisitor> features){
		this.features = features;
		this.config = config;
	}
	public void initializeSteps(){
		for(AbstractTextNodeVisitor feature:this.features){
			feature.initialize(this.config);
		}
	}
	@Override
	public Collection<AbstractTextNodeVisitor> getSteps(){
		return features;
	}
}
