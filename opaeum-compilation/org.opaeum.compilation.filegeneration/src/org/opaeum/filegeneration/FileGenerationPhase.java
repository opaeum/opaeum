package org.opaeum.filegeneration;

import java.util.Collection;
import java.util.List;

import org.opaeum.feature.InputModel;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.PhaseDependency;
import org.opaeum.feature.TransformationContext;
import org.opaeum.feature.TransformationPhase;
import org.opaeum.textmetamodel.TextOutputNode;
import org.opaeum.textmetamodel.TextWorkspace;

@PhaseDependency()
public class FileGenerationPhase implements TransformationPhase<AbstractTextNodeVisitor,TextOutputNode>{
	@InputModel
	private TextWorkspace textWorkspace;
	private OpaeumConfig config;
	private List<AbstractTextNodeVisitor> features;
	public void initialize(OpaeumConfig config){
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
	public void initialize(OpaeumConfig config,List<AbstractTextNodeVisitor> features){
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
