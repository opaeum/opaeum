package net.sf.nakeduml.javageneration.hibernate;

import java.util.List;

import net.sf.nakeduml.feature.InputModel;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.PhaseDependency;
import net.sf.nakeduml.feature.TransformationPhase;
import net.sf.nakeduml.filegeneration.FileGenerationPhase;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.auditing.AuditGenerationPhase;
import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

@PhaseDependency(before={FileGenerationPhase.class},after = {AuditGenerationPhase.class, JavaTransformationPhase.class})
public class StandaloneHibernatePhase implements TransformationPhase<StandaloneHibernateStep>{
	@InputModel
	protected OJPackage javaModel;
	@InputModel
	INakedModelWorkspace workspace;
	
	protected NakedUmlConfig config;
	@InputModel
	protected TextWorkspace textWorkspace;


	@Override
	public void initialize(NakedUmlConfig config) {
		this.config = config;
		
	}

	@Override
	public Object[] execute(List<StandaloneHibernateStep> features) {
		for (StandaloneHibernateStep step : features) {
			step.initialize(workspace,config,textWorkspace,javaModel);
			step.generate();
		}
		return new Object[]{textWorkspace};
	}

}
