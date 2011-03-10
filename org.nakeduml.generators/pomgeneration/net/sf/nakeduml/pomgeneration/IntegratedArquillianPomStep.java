package net.sf.nakeduml.pomgeneration;

import java.util.Collection;
import java.util.HashSet;

import net.sf.nakeduml.feature.OutputRoot;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.javageneration.JavaTextSource.OutputRootId;
import net.sf.nakeduml.javageneration.persistence.PersistenceStep;

import org.apache.maven.pom.Dependency;

@StepDependency(requires = {BasicJavaIntegratedAdaptorPomStep.class},before = {},after = {},phase = PomGenerationPhase.class)
public class IntegratedArquillianPomStep extends PomGenerationStep{
	@Override
	public Dependency[] getDependencies(){
		Collection<Dependency> result = new HashSet<Dependency>();
		addArquillian(result);
		return (Dependency[]) result.toArray(new Dependency[result.size()]);
	}

	@Override
	protected OutputRoot getExampleTargetDir(){
		return config.getOutputRoot(OutputRootId.INTEGRATED_ADAPTOR_GEN_SRC);
	}
	
}