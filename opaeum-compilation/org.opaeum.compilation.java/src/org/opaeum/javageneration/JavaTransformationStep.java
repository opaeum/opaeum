package org.opaeum.javageneration;

import java.util.Collection;

import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.ITransformationStep;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.TransformationContext;
import org.opaeum.java.metamodel.OJWorkspace;
import org.opaeum.textmetamodel.TextOutputNode;
import org.opaeum.textmetamodel.TextWorkspace;

public interface JavaTransformationStep extends ITransformationStep{
	public void initialize(OJWorkspace pac, OpaeumConfig config, TextWorkspace textWorkspace, EmfWorkspace workspace) ;
	public void setTransformationContext(TransformationContext c);
	public Collection<TextOutputNode> getTextFiles();
	public void release();
}

