package org.opeum.pomgeneration;

import java.util.Collection;
import java.util.HashSet;

import org.opeum.feature.StepDependency;
import org.opeum.textmetamodel.JavaSourceFolderIdentifier;
import org.opeum.textmetamodel.SourceFolderDefinition;

import org.apache.maven.pom.Dependency;
import org.apache.maven.pom.Profile;

@StepDependency(requires = {BasicIntegratedAdaptorPomStep.class},before = {},after = {},phase = PomGenerationPhase.class)
public class IntegratedArquillianPomStep extends PomGenerationStep{
	@Override
	public Profile[] getProfiles() {
		return new Profile[]{createArquillianProfile()};
	}

	@Override
	public Dependency[] getDependencies(){
		Collection<Dependency> result = new HashSet<Dependency>();
		addArquillian(result);
		//TODO figure out how to do arquillian tests without the web stuff
		addSeamServlet(result);
		addSeamServletImpl(result);
		return (Dependency[]) result.toArray(new Dependency[result.size()]);
	}

	@Override
	protected SourceFolderDefinition getExampleTargetDir(){
		return config.getSourceFolderDefinition(JavaSourceFolderIdentifier.INTEGRATED_ADAPTOR_GEN_SRC);
	}
	
	
}