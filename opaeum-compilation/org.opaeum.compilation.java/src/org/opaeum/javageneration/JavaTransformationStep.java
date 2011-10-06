package org.opaeum.javageneration;

import java.util.Collection;

import org.opaeum.feature.ITransformationStep;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.TransformationContext;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.metamodel.workspace.INakedModelWorkspace;
import org.opaeum.textmetamodel.TextOutputNode;
import org.opaeum.textmetamodel.TextWorkspace;

public interface JavaTransformationStep extends ITransformationStep{
	public void initialize(OJPackage pac, OpaeumConfig config, TextWorkspace textWorkspace, INakedModelWorkspace workspace) ;
	public void setTransformationContext(TransformationContext c);
	public Collection<? extends TextOutputNode> getTextFiles();
}

