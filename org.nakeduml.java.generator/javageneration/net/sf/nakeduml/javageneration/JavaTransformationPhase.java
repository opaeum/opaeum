package net.sf.nakeduml.javageneration;

import java.util.List;

import net.sf.nakeduml.feature.InputModel;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.OutputModel;
import net.sf.nakeduml.feature.PhaseDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.TransformationPhase;
import net.sf.nakeduml.filegeneration.FileGenerationPhase;
import net.sf.nakeduml.linkage.LinkagePhase;
import net.sf.nakeduml.linkage.OclParsingPhase;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.TextWorkspace;
import net.sf.nakeduml.validation.namegeneration.NameGenerationPhase;

import org.nakeduml.java.metamodel.annotation.OJAnnotatedPackage;

@PhaseDependency(after = { LinkagePhase.class,
		NameGenerationPhase.class,OclParsingPhase.class }, before = { FileGenerationPhase.class })
public class JavaTransformationPhase implements TransformationPhase<AbstractJavaTransformationStep> {
	private static JavaTransformationPhase INSTANCE = new JavaTransformationPhase();
	@InputModel
	private TextWorkspace textWorkspace;
	@InputModel
	private INakedModelWorkspace modelWorkspace;
	@OutputModel
	OJAnnotatedPackage javaModel;
	private NakedUmlConfig config;
	public static final boolean IS_RUNTIME_AVAILABLE = false;

	public void initialize(NakedUmlConfig config) {
		this.config = config;
	}

	public Object[] execute(List<AbstractJavaTransformationStep> features,TransformationContext context) {
		javaModel = new OJAnnotatedPackage();
		for (AbstractJavaTransformationStep f : features) {
			f.initialize(javaModel, config, textWorkspace);
			f.generate(modelWorkspace, context);
			context.featureApplied(f.getClass());
		}
		return new Object[] { javaModel };
	}

	private TextWorkspace getTextWorkspaceInternal() {
		return textWorkspace;
	}

	public static TextWorkspace getTextWorkspace() {
		return INSTANCE.getTextWorkspaceInternal();
	}
}
