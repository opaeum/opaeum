package org.opeum.javageneration;

import java.util.Collection;
import java.util.Collections;

import org.opeum.feature.OpeumConfig;
import org.opeum.feature.TransformationContext;
import org.opeum.java.metamodel.OJPackage;
import org.opeum.metamodel.workspace.INakedModelWorkspace;
import org.opeum.textmetamodel.TextFile;
import org.opeum.textmetamodel.TextWorkspace;

public abstract class JavaFeature implements JavaTransformationStep{
	@Override
	public void setTransformationContext(TransformationContext c){
	}
	@Override
	public Collection<? extends TextFile> getTextFiles(){
		return Collections.emptySet();
	}
	@Override
	public void initialize(OJPackage pac,OpeumConfig config,TextWorkspace textWorkspace, INakedModelWorkspace workspace){
	}
}
