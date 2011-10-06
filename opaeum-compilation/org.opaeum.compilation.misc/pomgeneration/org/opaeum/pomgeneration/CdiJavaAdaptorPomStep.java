package org.opaeum.pomgeneration;

import java.util.Collection;

import org.opaeum.feature.StepDependency;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;
import org.opaeum.textmetamodel.SourceFolderDefinition;

import org.apache.maven.pom.Dependency;
import org.opaeum.generation.features.JavaPersistence;

@StepDependency(requires = { JavaPersistence.class }, before = {}, after = {}, phase = PomGenerationPhase.class)
public class CdiJavaAdaptorPomStep extends PomGenerationStep {
	@Override
	public Dependency[] getDependencies() {
		Collection<Dependency> result = getBasicDependencies(JavaSourceFolderIdentifier.ADAPTOR_GEN_SRC);
		addDependencyToRootObject(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC, model, result);
		addNumlTestAdaptor(result);
		//Provided dependencies from above projects
		addCdi(result);
		addJbossJee6Spec(result);
		return (Dependency[]) result.toArray(new Dependency[result.size()]);
	}
	

	@Override
	protected SourceFolderDefinition getExampleTargetDir() {
		return config.getSourceFolderDefinition(JavaSourceFolderIdentifier.ADAPTOR_GEN_SRC);
	}
	
	
	
}
