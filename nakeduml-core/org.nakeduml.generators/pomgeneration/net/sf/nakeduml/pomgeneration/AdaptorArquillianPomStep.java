package net.sf.nakeduml.pomgeneration;

import java.util.Collection;
import java.util.HashSet;

import net.sf.nakeduml.feature.OutputRoot;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.javageneration.JavaTextSource.OutputRootId;
import net.sf.nakeduml.javageneration.persistence.PersistenceStep;

import org.apache.maven.pom.Dependency;
import org.apache.maven.pom.Profile;

@StepDependency(requires = {PersistenceStep.class, BasicJavaAdaptorPomStep.class},before = {},after = {},phase = PomGenerationPhase.class)
public class AdaptorArquillianPomStep extends PomGenerationStep{
	@Override
	public Dependency[] getDependencies(){
		//TODO compilation error testing jenkins
		Collection<Plugin> result = new HashSet<Dependency>();
		addArquillian(result);
		return (Dependency[]) result.toArray(new Dependency[result.size()]);
	}

	@Override
	protected OutputRoot getExampleTargetDir(){
		return config.getOutputRoot(OutputRootId.ADAPTOR_GEN_SRC);
	}

	@Override
	public Profile[] getProfiles() {
		return new Profile[]{createArquillianProfile()};
	}

	
}