package net.sf.nakeduml.javageneration.hibernate.hbm;

import net.hibernatehbmmetamodel.HbmWorkspace;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

public abstract class AbstractHbmTransformationStep implements TransformationStep {
	protected OJPackage javaModel;
	protected NakedUmlConfig config;
	protected TextWorkspace textWorkspace;
	protected HbmWorkspace hbmWorkspace;

	public void initialize(OJPackage pac, NakedUmlConfig config, TextWorkspace textWorkspace, HbmWorkspace hbmWorkspace) {
		this.javaModel = pac;
		this.config = config;
		this.textWorkspace = textWorkspace;
		this.hbmWorkspace = hbmWorkspace;
	}

	public abstract void generate(INakedModelWorkspace workspace, TransformationContext context);
}
