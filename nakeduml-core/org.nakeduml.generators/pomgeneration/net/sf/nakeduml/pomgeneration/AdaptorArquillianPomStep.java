package net.sf.nakeduml.pomgeneration;

import java.util.Collection;
import java.util.HashSet;

import net.sf.nakeduml.feature.SourceFolderDefinition;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.javageneration.JavaSourceFolderIdentifier;

import org.apache.maven.pom.Dependency;
import org.apache.maven.pom.Profile;
import org.nakeduml.generation.features.JavaPersistence;

@StepDependency(requires = {JavaPersistence.class, BasicJavaAdaptorPomStep.class},before = {},after = {},phase = PomGenerationPhase.class)
public class AdaptorArquillianPomStep extends PomGenerationStep{
	@Override
	public Dependency[] getDependencies(){
		Collection<Dependency> result = new HashSet<Dependency>();
		addArquillian(result);
		return (Dependency[]) result.toArray(new Dependency[result.size()]);
	}

	@Override
	protected SourceFolderDefinition getExampleTargetDir(){
		return config.getSourceFolderDefinition(JavaSourceFolderIdentifier.ADAPTOR_GEN_SRC);
	}

	@Override
	public Profile[] getProfiles() {
		return new Profile[]{createArquillianProfile()};
	}

	
}