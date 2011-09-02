package net.sf.nakeduml.javageneration;

import java.util.Collection;

import net.sf.nakeduml.feature.ITransformationStep;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.TextOutputNode;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

import org.nakeduml.java.metamodel.annotation.OJAnnotatedPackage;

public interface JavaTransformationStep extends ITransformationStep{
	public void initialize(OJAnnotatedPackage pac, NakedUmlConfig config, TextWorkspace textWorkspace, INakedModelWorkspace workspace) ;
	public void setTransformationContext(TransformationContext c);
	public Collection<? extends TextOutputNode> getTextFiles();
}

