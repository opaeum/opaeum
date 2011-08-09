package net.sf.nakeduml.javageneration;

import java.util.Collection;
import java.util.Collections;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.TextFile;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

import org.nakeduml.java.metamodel.annotation.OJAnnotatedPackage;

public abstract class JavaFeature implements JavaTransformationStep{
	@Override
	public void setTransformationContext(TransformationContext c){
	}
	@Override
	public Collection<? extends TextFile> getTextFiles(){
		return Collections.emptySet();
	}
	@Override
	public void initialize(OJAnnotatedPackage pac,NakedUmlConfig config,TextWorkspace textWorkspace, INakedModelWorkspace workspace){
	}
}
