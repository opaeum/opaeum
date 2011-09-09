package net.sf.nakeduml.pomgeneration;

import java.util.Collection;

import net.sf.nakeduml.feature.SourceFolderDefinition;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.javageneration.JavaSourceFolderIdentifier;

import org.apache.maven.pom.Dependency;
import org.nakeduml.generation.features.JavaPersistence;

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
