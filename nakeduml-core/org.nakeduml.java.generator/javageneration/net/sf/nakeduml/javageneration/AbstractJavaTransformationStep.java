package net.sf.nakeduml.javageneration;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

import org.nakeduml.java.metamodel.annotation.OJAnnotatedPackage;


public abstract class AbstractJavaTransformationStep implements JavaTransformationStep {
	protected OJAnnotatedPackage javaModel;
	protected NakedUmlConfig config;
	protected TextWorkspace textWorkspace;

	



}
