package net.sf.nakeduml.javageneration;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

import org.nakeduml.java.metamodel.annotation.OJAnnotatedPackage;

public interface JavaTransformationStep extends TransformationStep{
	public void initialize(OJAnnotatedPackage pac, NakedUmlConfig config, TextWorkspace textWorkspace, TransformationContext context) ;


}

