package org.opaeum.javageneration;

import java.util.Collection;
import java.util.Collections;

import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.TransformationContext;
import org.opaeum.java.metamodel.OJWorkspace;
import org.opaeum.metamodel.workspace.INakedModelWorkspace;
import org.opaeum.textmetamodel.TextOutputNode;
import org.opaeum.textmetamodel.TextWorkspace;

public abstract class JavaFeature implements JavaTransformationStep{
	public void release(){};
	@Override
	public void setTransformationContext(TransformationContext c){
	}
	@Override
	public Collection<TextOutputNode> getTextFiles(){
		return Collections.emptySet();
	}
	@Override
	public void initialize(OJWorkspace pac,OpaeumConfig config,TextWorkspace textWorkspace, INakedModelWorkspace workspace){
	}
}
