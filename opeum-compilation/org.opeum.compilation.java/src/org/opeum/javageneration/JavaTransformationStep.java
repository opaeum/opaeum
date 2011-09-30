package org.opeum.javageneration;

import java.util.Collection;

import org.opeum.feature.ITransformationStep;
import org.opeum.feature.OpeumConfig;
import org.opeum.feature.TransformationContext;
import org.opeum.java.metamodel.OJPackage;
import org.opeum.metamodel.workspace.INakedModelWorkspace;
import org.opeum.textmetamodel.TextOutputNode;
import org.opeum.textmetamodel.TextWorkspace;

public interface JavaTransformationStep extends ITransformationStep{
	public void initialize(OJPackage pac, OpeumConfig config, TextWorkspace textWorkspace, INakedModelWorkspace workspace) ;
	public void setTransformationContext(TransformationContext c);
	public Collection<? extends TextOutputNode> getTextFiles();
}

