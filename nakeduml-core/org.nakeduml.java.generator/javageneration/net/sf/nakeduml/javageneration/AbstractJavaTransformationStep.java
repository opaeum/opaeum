package net.sf.nakeduml.javageneration;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

import org.nakeduml.java.metamodel.OJPackage;


public abstract class AbstractJavaTransformationStep implements JavaTransformationStep {
	protected OJPackage javaModel;
	protected NakedUmlConfig config;
	protected TextWorkspace textWorkspace;

	



}
