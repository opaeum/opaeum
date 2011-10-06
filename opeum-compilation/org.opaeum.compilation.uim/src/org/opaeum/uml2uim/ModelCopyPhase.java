package org.opeum.uml2uim;

import java.util.Collection;
import java.util.List;

import org.opeum.emf.workspace.EmfWorkspace;
import org.opeum.feature.InputModel;
import org.opeum.feature.IntegrationPhase;
import org.opeum.feature.OpeumConfig;
import org.opeum.feature.PhaseDependency;
import org.opeum.feature.TransformationContext;
import org.opeum.feature.TransformationPhase;
import org.opeum.textmetamodel.TextWorkspace;

@PhaseDependency(after = UimSynchronizationPhase.class,before = {})
public class ModelCopyPhase implements TransformationPhase<ModelCopyStep,EmfWorkspace>,IntegrationPhase{
	@InputModel
	EmfWorkspace emfWorkspace;
	@InputModel
	TextWorkspace textWorkspace;
	private OpeumConfig config;
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
	public void initialize(OpeumConfig config,List<ModelCopyStep> features){
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
