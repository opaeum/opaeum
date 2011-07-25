package org.nakeduml.tinker.auditing;

import java.util.List;

import net.sf.nakeduml.feature.InputModel;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.PhaseDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.TransformationPhase;
import net.sf.nakeduml.filegeneration.FileGenerationPhase;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.JavaTransformationStep;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

import org.nakeduml.java.metamodel.annotation.OJAnnotatedPackage;

@PhaseDependency(after = { JavaTransformationPhase.class }, before={FileGenerationPhase.class})
public class TinkerAuditGenerationPhase implements TransformationPhase<AbstractJavaTransformationStep, INakedElement> {
	private NakedUmlConfig config;
	@InputModel
	private TextWorkspace textWorkspace;
	@InputModel
	INakedModelWorkspace workspace;
	@InputModel
	OJAnnotatedPackage javaModel;
	

	@Override
	public void initialize(NakedUmlConfig config) {
		this.config = config;
	}

	@Override
	public Object[] execute(List<AbstractJavaTransformationStep> features,TransformationContext context) {
		for (JavaTransformationStep a : features) {
			a.initialize(javaModel, config, textWorkspace);
			a.generate(workspace, context);
		}
		return new Object[] { javaModel };
	}

	@Override
	public Object processSingleElement(List<AbstractJavaTransformationStep> features,TransformationContext context,INakedElement element){
		// TODO Auto-generated method stub
		return null;
	}
}
