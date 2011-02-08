package net.sf.nakeduml.javageneration.auditing;

import java.util.List;

import net.sf.nakeduml.feature.InputModel;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.PhaseDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.TransformationPhase;
import net.sf.nakeduml.filegeneration.FileGenerationPhase;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedPackage;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

@PhaseDependency(after = { JavaTransformationPhase.class }, before={FileGenerationPhase.class})
public class AuditGenerationPhase implements TransformationPhase<AuditImplementationStep> {
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
	public Object[] execute(List<AuditImplementationStep> features) {
		OJPackage auditRoot = new OJPackage();
		TransformationContext context = new TransformationContext();
		for (AuditImplementationStep a : features) {
			a.initialize(javaModel, config, textWorkspace,auditRoot);
			a.generate(workspace, context);
		}
		return new Object[] { javaModel };
	}
}
