package org.nakeduml.uml2uim;

import java.util.Collection;
import java.util.List;

import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.feature.InputModel;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.PhaseDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.TransformationPhase;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

@PhaseDependency(after = UimSynchronizationPhase.class)
public class ModelCopyPhase implements TransformationPhase<ModelCopyStep,EmfWorkspace>{
	@InputModel
	EmfWorkspace emfWorkspace;
	@InputModel
	TextWorkspace textWorkspace;
	private NakedUmlConfig config;
	private List<ModelCopyStep> features;
	@Override
	public void initialize(NakedUmlConfig config){
		this.config = config;
	}
	@Override
	public Object[] execute(List<ModelCopyStep> features,TransformationContext context){
		this.features=features;
		for(ModelCopyStep step:features){
			step.init(config, textWorkspace);
			step.startVisiting(emfWorkspace);
		}
		return new Object[]{
			textWorkspace
		};
	}
	@Override
	public Collection<?> processElements(TransformationContext context,Collection<EmfWorkspace> elements){
		for(EmfWorkspace element:elements){
			for(ModelCopyStep step:features){
				step.init(config, textWorkspace);
				step.visitOnly(element);
			}
		}
		return elements;
	}
}
