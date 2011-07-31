package net.sf.nakeduml.javageneration;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

import org.nakeduml.java.metamodel.annotation.OJAnnotatedPackage;

public abstract class JavaFeature implements JavaTransformationStep{
	protected OJAnnotatedPackage javaModel;
	protected NakedUmlConfig config;
	protected TextWorkspace textWorkspace;
	@Override
	public void initialize(OJAnnotatedPackage pac,NakedUmlConfig config,TextWorkspace textWorkspace,TransformationContext context){
		this.javaModel = pac;
		this.config = config;
		this.textWorkspace = textWorkspace;
	}
}
