package org.opeum.filegeneration;

import java.util.Collection;
import java.util.List;

import org.opeum.feature.InputModel;
import org.opeum.feature.OpeumConfig;
import org.opeum.feature.PhaseDependency;
import org.opeum.feature.TransformationContext;
import org.opeum.feature.TransformationPhase;
import org.opeum.textmetamodel.TextOutputNode;
import org.opeum.textmetamodel.TextWorkspace;

@PhaseDependency()
public class FileGenerationPhase implements TransformationPhase<AbstractTextNodeVisitor,TextOutputNode>{
	@InputModel
	private TextWorkspace textWorkspace;
	private OpeumConfig config;
	private List<AbstractTextNodeVisitor> features;
	public void initialize(OpeumConfig config){
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
	public void initialize(OpeumConfig config,List<AbstractTextNodeVisitor> features){
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
