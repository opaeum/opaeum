package org.opaeum.eclipse.starter;

import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.opaeum.bootstrap.BootstrapGenerationPhase;
import org.opaeum.feature.InputModel;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.PhaseDependency;
import org.opaeum.feature.TransformationContext;
import org.opaeum.feature.TransformationPhase;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.jbpm5.FlowGenerationPhase;
import org.opaeum.textmetamodel.TextOutputNode;
import org.opaeum.textmetamodel.TextWorkspace;
@PhaseDependency(after={JavaTransformationPhase.class, FlowGenerationPhase.class, BootstrapGenerationPhase.class})
public class EclipseProjectGenerationPhase implements TransformationPhase<EclipseProjectGenerationStep,TextOutputNode>{
	IWorkspaceRoot workspaceRoot;
	@InputModel
	TextWorkspace textWorkspace;
	private OpaeumConfig config;
	private List<EclipseProjectGenerationStep> features;
	@Override
	public void initialize(OpaeumConfig config, List<EclipseProjectGenerationStep> features){
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
