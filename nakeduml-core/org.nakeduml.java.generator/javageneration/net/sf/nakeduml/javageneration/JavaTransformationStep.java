package net.sf.nakeduml.javageneration;

import org.nakeduml.java.metamodel.annotation.OJAnnotatedPackage;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

public interface JavaTransformationStep extends TransformationStep{
	public void initialize(OJAnnotatedPackage pac, NakedUmlConfig config, TextWorkspace textWorkspace) ;

	public abstract void generate(INakedModelWorkspace workspace, TransformationContext context);

}

