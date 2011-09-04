package org.nakeduml.uml2uim;

import java.util.Collection;
import java.util.List;

import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.feature.InputModel;
import net.sf.nakeduml.feature.IntegrationPhase;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.PhaseDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.TransformationPhase;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

@PhaseDependency(after = UimSynchronizationPhase.class,before = {
	JavaTransformationPhase.class
})
public class ModelCopyPhase implements TransformationPhase<ModelCopyStep,EmfWorkspace>,IntegrationPhase{
	@InputModel
	EmfWorkspace emfWorkspace;
	@InputModel
	TextWorkspace textWorkspace;
	private NakedUmlConfig config;
	private List<ModelCopyStep> features;
	@Override
	public Collection<?> processElements(TransformationContext context,Collection<EmfWorkspace> elements){
		for(EmfWorkspace element:elements){
			for(ModelCopyStep step:features){
				step.visitOnly(element);
			}
		}
		return elements;
	}
	@Override
	public void execute(TransformationContext context){
		for(ModelCopyStep step:features){
			if(!context.getLog().isCanceled()){
				step.startVisiting(emfWorkspace);
			}
		}
	}
	@Override
	public void initialize(NakedUmlConfig config,List<ModelCopyStep> features){
		this.config = config;
		this.features = features;
	}
	public void initializeSteps(){
		for(ModelCopyStep step:this.features){
			step.init(this.config, textWorkspace);
		}
	}
	@Override
	public Collection<ModelCopyStep> getSteps(){
		return features;
	}
}
