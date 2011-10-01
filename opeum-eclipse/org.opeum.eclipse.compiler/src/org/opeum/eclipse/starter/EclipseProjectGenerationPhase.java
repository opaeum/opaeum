package org.opeum.eclipse.starter;

import java.util.Collection;
import java.util.List;

import net.sf.opeum.feature.InputModel;
import net.sf.opeum.feature.NakedUmlConfig;
import net.sf.opeum.feature.PhaseDependency;
import net.sf.opeum.feature.TransformationContext;
import net.sf.opeum.feature.TransformationPhase;
import net.sf.opeum.javageneration.JavaTransformationPhase;
import net.sf.opeum.jbpm5.FlowGenerationPhase;
import net.sf.opeum.textmetamodel.TextOutputNode;
import net.sf.opeum.textmetamodel.TextWorkspace;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.opeum.bootstrap.BootstrapGenerationPhase;
@PhaseDependency(after={JavaTransformationPhase.class, FlowGenerationPhase.class, BootstrapGenerationPhase.class})
public class EclipseProjectGenerationPhase implements TransformationPhase<EclipseProjectGenerationStep,TextOutputNode>{
	IWorkspaceRoot workspaceRoot;
	@InputModel
	TextWorkspace textWorkspace;
	private NakedUmlConfig config;
	private List<EclipseProjectGenerationStep> features;
	@Override
	public void initialize(NakedUmlConfig config, List<EclipseProjectGenerationStep> features){
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
