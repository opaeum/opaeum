package org.opaeum.uim.uml2uim;

import java.util.Collection;
import java.util.List;

import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.InputModel;
import org.opaeum.feature.IntegrationPhase;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.PhaseDependency;
import org.opaeum.feature.TransformationContext;
import org.opaeum.feature.TransformationPhase;
import org.opaeum.textmetamodel.TextWorkspace;

@PhaseDependency(after = UimSynchronizationPhase.class,before = {})
public class ModelCopyPhase implements TransformationPhase<ModelCopyStep,EmfWorkspace>,IntegrationPhase{
	@InputModel
	EmfWorkspace emfWorkspace;
	@InputModel
	TextWorkspace textWorkspace;
	private OpaeumConfig config;
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
	public void initialize(OpaeumConfig config,List<ModelCopyStep> features){
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
	@Override
	public void release(){
		this.emfWorkspace=null;
		this.textWorkspace=null;
		for(ModelCopyStep modelCopyStep:this.features){
			modelCopyStep.release();
		}
		
	}
}
