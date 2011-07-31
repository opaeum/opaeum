package net.sf.nakeduml.filegeneration;

import java.util.Collection;
import java.util.List;

import net.sf.nakeduml.feature.InputModel;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.PhaseDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.TransformationPhase;
import net.sf.nakeduml.textmetamodel.TextOutputNode;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

@PhaseDependency()
public class FileGenerationPhase implements TransformationPhase<AbstractTextNodeVisitor,TextOutputNode> {
	@InputModel
	private TextWorkspace textWorkspace;
	private NakedUmlConfig config;
	private List<AbstractTextNodeVisitor> features;
	public void initialize(NakedUmlConfig config) {
		this.config=config;
	}

	public Object[] execute(List<AbstractTextNodeVisitor> features,TransformationContext context) {
		this.features=features;
		for (AbstractTextNodeVisitor feature : features) {
			feature.initialize(config);
			feature.startVisiting(textWorkspace);
		}
		return new Object[]{};
		
	}

	@Override
	public Collection<?> processElements(TransformationContext context,Collection<TextOutputNode> elements){
		for(TextOutputNode element:elements){
			for (AbstractTextNodeVisitor feature : features) {
				feature.initialize(config);
				feature.visitRecursively(element);
			}
		}
		return elements;
	}
}
