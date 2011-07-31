package org.nakeduml.eclipse.starter;

import java.util.Collection;
import java.util.List;

import net.sf.nakeduml.feature.InputModel;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.PhaseDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.TransformationPhase;
import net.sf.nakeduml.filegeneration.FileGenerationPhase;
import net.sf.nakeduml.textmetamodel.TextOutputNode;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
@PhaseDependency(before={FileGenerationPhase.class})
public class EclipseProjectGenerationPhase implements TransformationPhase<EclipseProjectGenerationStep,TextOutputNode>{
	IWorkspaceRoot workspaceRoot;
	@InputModel
	TextWorkspace textWorkspace;
	private NakedUmlConfig config;
	@Override
	public void initialize(NakedUmlConfig config){
		this.config = config;
		workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
	}
	@Override
	public Object[] execute(List<EclipseProjectGenerationStep> features,TransformationContext context){
		for(EclipseProjectGenerationStep step:features){
			step.initialize(workspaceRoot, config);
			step.startVisiting(textWorkspace);
		}
		return new Object[]{workspaceRoot};
	}
	@Override
	public Collection<?> processElements(TransformationContext context,Collection<TextOutputNode> elements){
		return elements;
	}
}
