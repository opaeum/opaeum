package net.sf.nakeduml.javageneration.hibernate.hbm;

import java.util.List;

import net.hibernatehbmmetamodel.HbmWorkspace;
import net.sf.nakeduml.feature.InputModel;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.PhaseDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.TransformationPhase;
import net.sf.nakeduml.filegeneration.FileGenerationPhase;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.linkage.LinkagePhase;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.TextWorkspace;
import net.sf.nakeduml.validation.namegeneration.NameGenerationPhase;

@PhaseDependency(after = { LinkagePhase.class,
		NameGenerationPhase.class, JavaTransformationPhase.class }, before = { FileGenerationPhase.class })
public class HibernateHbmPhase implements TransformationPhase<AbstractHbmTransformationStep> {

	private static HibernateHbmPhase INSTANCE = new HibernateHbmPhase();
	@InputModel
	private TextWorkspace textWorkspace;
	@InputModel
	private INakedModelWorkspace modelWorkspace;
	private NakedUmlConfig config;
	@InputModel
	private HbmWorkspace hbmWorkspace;
	@InputModel
	private OJPackage javaModel;	

	public void initialize(NakedUmlConfig config) {
		this.config = config;
	}

	public Object[] execute(List<AbstractHbmTransformationStep> features) {
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxxx");
		TransformationContext context = new TransformationContext();
		for (AbstractHbmTransformationStep f : features) {
			f.initialize(javaModel, config, textWorkspace, hbmWorkspace);
			f.generate(modelWorkspace, context);
		}
		return new Object[] { hbmWorkspace };
	}

	private TextWorkspace getTextWorkspaceInternal() {
		return textWorkspace;
	}

	public static TextWorkspace getTextWorkspace() {
		return INSTANCE.getTextWorkspaceInternal();
	}

}
