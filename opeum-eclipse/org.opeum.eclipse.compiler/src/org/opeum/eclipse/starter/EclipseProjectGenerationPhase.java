package org.opeum.eclipse.starter;

import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.opeum.bootstrap.BootstrapGenerationPhase;
import org.opeum.feature.InputModel;
import org.opeum.feature.OpeumConfig;
import org.opeum.feature.PhaseDependency;
import org.opeum.feature.TransformationContext;
import org.opeum.feature.TransformationPhase;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.jbpm5.FlowGenerationPhase;
import org.opeum.textmetamodel.TextOutputNode;
import org.opeum.textmetamodel.TextWorkspace;
@PhaseDependency(after={JavaTransformationPhase.class, FlowGenerationPhase.class, BootstrapGenerationPhase.class})
public class EclipseProjectGenerationPhase implements TransformationPhase<EclipseProjectGenerationStep,TextOutputNode>{
	IWorkspaceRoot workspaceRoot;
	@InputModel
	TextWorkspace textWorkspace;
	private OpeumConfig config;
	private List<EclipseProjectGenerationStep> features;
	@Override
	public void initialize(OpeumConfig config, List<EclipseProjectGenerationStep> features){
		this.config = config;
		this.features=features;
		workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
	}
	public void initializeSteps(){
		for(EclipseProjectGenerationStep step:this.features){
			step.initialize(workspaceRoot, this.config);
		}
	}
	@Override
	public void execute(TransformationContext context){
		for(EclipseProjectGenerationStep step:features){
			step.startVisiting(textWorkspace);
		}
	}
	@Override
	public Collection<?> processElements(TransformationContext context,Collection<TextOutputNode> elements){
		return elements;
	}
	@Override
	public Collection<EclipseProjectGenerationStep> getSteps(){
		return features;
	}
}
