package net.sf.nakeduml.javageneration;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedPackage;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

public abstract class AbstractJavaTransformationStep implements TransformationStep {
	protected OJAnnotatedPackage javaModel;
	protected NakedUmlConfig config;
	protected TextWorkspace textWorkspace;

	public void initialize(OJAnnotatedPackage pac, NakedUmlConfig config, TextWorkspace textWorkspace) {
		this.javaModel = pac;
		this.config = config;
		this.textWorkspace = textWorkspace;
	}

	public abstract void generate(INakedModelWorkspace workspace, TransformationContext context);


}
