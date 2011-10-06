package org.opeum.pomgeneration;

import java.util.ArrayList;
import java.util.Collection;

import org.opeum.feature.StepDependency;
import org.opeum.textmetamodel.JavaSourceFolderIdentifier;
import org.opeum.textmetamodel.SourceFolderDefinition;

import org.apache.maven.pom.Dependency;

@StepDependency(requires = {},before = {},after = {},phase = PomGenerationPhase.class)
public class CdiIntegratedAdaptorPomStep extends PomGenerationStep{
	@Override
	public boolean isIntegrationStep(){
		return true;
	}
	@Override
	public Dependency[] getDependencies(){
		Collection<Dependency> dependencies = new ArrayList<Dependency>();
		addJbossJee6Spec(dependencies);
		addCdi(dependencies);
		addSeamConfig(dependencies);
		return dependencies.toArray(new Dependency[dependencies.size()]);
	}
	@Override
	protected SourceFolderDefinition getExampleTargetDir(){
		return config.getSourceFolderDefinition(JavaSourceFolderIdentifier.INTEGRATED_ADAPTOR_GEN_SRC);
	}
}
